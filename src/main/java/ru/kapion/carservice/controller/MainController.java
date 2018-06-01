package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.security.user.UserAuthDto;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.MainService;
import ru.kapion.carservice.utils.DicHelper;
import ru.kapion.carservice.utils.ModelHelper;


@Controller
@RequestMapping("/")
public class MainController{

    private MainService service;
    private UserAuthDto userAuthDto;

    public MainController(MainService service, UserAuthDto userAuthDto) {
        this.service = service;
        this.userAuthDto = userAuthDto;
    }

    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("countCars", service.getCountCars());
        model.addAttribute("countRepairs", service.getCountRepairs());
        model.addAttribute("countRepairsComplete", service.getCountRepairsComplete());
        model.addAttribute("countOwners", service.getCountOwners());
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "main";
    }


    @RequestMapping(value = "/login")
    public String loginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }

        return "login";
    }

}
