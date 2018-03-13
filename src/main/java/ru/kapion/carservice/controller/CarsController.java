package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.utils.DicHelper;


@Controller
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarService service;

    @RequestMapping
    public String carsPage(Model model) {
        model.addAttribute("cars", service.getAll());
        return "cars";
    }

    @RequestMapping(value = "/addCar")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("title", "Добавление автомобиля");
        return "car";
    }

    @PostMapping(value = "/car/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        service.save(car);
        return "redirect:../";
    }

    @GetMapping(value = "/car/{id}")
    public String editCar(@PathVariable Integer id, Model model) {
        model.addAttribute("car", service.getById(id));
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("title", "Изменение данных автомобиля");
        model.addAttribute("repairs", service.getRepairs(id));
        return "car";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteCar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:../";
    }
}
