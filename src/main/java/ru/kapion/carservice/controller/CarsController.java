package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.OwnerService;
import ru.kapion.carservice.utils.DicHelper;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/cars")
public class CarsController implements SecurityCheck{


    @Autowired
    private CarService service;

    @Autowired
    private OwnerService ownerService;

    @RequestMapping
    public String carsPage(Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("cars", service.getAll());
        return "cars";
    }

    @RequestMapping(value = "/addcar")
    public String createCar(Model model) {

        Car car = new Car();
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("owners", ownerService.getAll(true));
        model.addAttribute("car", car);
        model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995, LocalDate.now().getYear()));
        model.addAttribute("title", "Добавление автомобиля");
        return "addcar";
    }

    @PostMapping(value = "/car/save")
    public String saveCar(@ModelAttribute("сar") Car car) {
        //Вытаскиваем все данные о пользователе и сохраняем, если он новый
        Integer clientId  = car.getOwner().getId();

        List<Owner> ownerList = ownerService.getAll(true);
        ownerList.stream()
                .filter(o -> o.getId().equals(clientId))
                .findFirst()
                .ifPresent(ownerService::save);

        Owner newOwner = ownerService.getById(clientId);

        car.setOwner(newOwner);

        service.save(car);
        return "redirect:../";
    }

    @GetMapping(value = "/car/{id}")
    public String editCar(@PathVariable Integer id, Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("car", service.getById(id));
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
        model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995,LocalDate.now().getYear()));
        model.addAttribute("title", "Изменение данных автомобиля");
        model.addAttribute("repairs", service.getRepairs(id));
        model.addAttribute("owner", service.getOwner(id));
        return "car";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteCar(@PathVariable Integer id) {

        service.delete(id);
        return "redirect:../";
    }


}
