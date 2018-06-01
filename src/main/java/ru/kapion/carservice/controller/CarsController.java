package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.security.user.UserAuthDto;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.OwnerService;
import ru.kapion.carservice.utils.DicHelper;
import ru.kapion.carservice.utils.ModelHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cars")
public class CarsController {

    private CarService service;
    private OwnerService ownerService;
    private UserAuthDto userAuthDto;

    public CarsController(CarService service, OwnerService ownerService, UserAuthDto userAuthDto) {
        this.service = service;
        this.ownerService = ownerService;
        this.userAuthDto = userAuthDto;
    }

    @RequestMapping
    public String carsPage(Model model) {
        model.addAttribute("cars", service.getAll());
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "cars";
    }

    @RequestMapping(value = "/addcar")
    public String createCar(Model model) {
        Car car = new Car();
        model.addAttribute("owners", ownerService.getAll(true));
        model.addAttribute("car", car);
        model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995, LocalDate.now().getYear()));
        model.addAttribute("title", "Добавление автомобиля");
        model.addAttribute("errorMessage", "");
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "addcar";
    }

    @PostMapping(value = "/car/save")
    public synchronized ModelAndView saveCar(@ModelAttribute("сar") Car car, Model model,
                                             RedirectAttributes redirectAttributes) throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView();
        if (service.isExist(car)) {
            //выводим ту же форму с отображением ошибки
            model.addAttribute("errorMessage", "Автомобиль с таким номером уже существует!");
            model.addAttribute("title", "Добавление автомобиля");
            model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
            model.addAttribute("engineTypes", DicHelper.getEngineTypes());
            model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995, LocalDate.now().getYear()));
            model.addAttribute("car", car);
            model.addAttribute("owners", ownerService.getAll(true));
            modelAndView.setViewName("addcar");
        }else {
            Integer clientId = car.getOwner().getId();

            //проверяем существование клиента
            Optional<Owner> client = Optional.ofNullable(ownerService.getById(clientId));
            if (!client.isPresent()) {
                //подставляем виртуального
                clientId = ownerService.save(ownerService.createVirtual());
            }

            Optional<Owner>  newOwner = Optional.ofNullable(ownerService.getById(clientId));
            if (newOwner.isPresent()) {
                car.setOwner(newOwner.get());
                service.save(car);
            }else{
                throw new RuntimeException("Не удалось определить владельца");
            }

            RedirectView redirectView = new RedirectView("../");
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сохранено");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            //return "redirect:../";
        }
        return modelAndView;
    }

    @GetMapping(value = "/car/{id}")
    public String editCar(@PathVariable Integer id, Model model) {
        model.addAttribute("car", service.getById(id));
        model.addAttribute("engineTypes", DicHelper.getEngineTypes());
        model.addAttribute("engineCapacities", DicHelper.getCapacity(1.0,5.0));
        model.addAttribute("yearBuildOut", DicHelper.getYearBuildOut(1995,LocalDate.now().getYear()));
        model.addAttribute("title", "Изменение данных автомобиля");
        model.addAttribute("repairs", service.getRepairs(id));
        model.addAttribute("owner", service.getOwner(id));
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "car";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteCar(@PathVariable Integer id) {

        service.delete(id);
        return "redirect:../";
    }


}
