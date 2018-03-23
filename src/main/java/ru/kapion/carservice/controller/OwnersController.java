package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.OwnerService;
import ru.kapion.carservice.utils.DicHelper;


@Controller
@RequestMapping("/clients")
public class OwnersController implements SecurityCheck {

    @Autowired
    private OwnerService service;

    @RequestMapping
    public String ownersPage(Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("owners", service.getAll(false));
        return "clients";
    }

    @RequestMapping(value = "/add")
    public String createOwner(Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("owner", new Owner());
        model.addAttribute("title", "Добавление клиента");
        return "addowner";
    }

    @PostMapping(value = "/owner/save")
    public String saveOwner(@ModelAttribute("owner") Owner owner) {
        service.save(owner);
        return "redirect:../";
    }

    @GetMapping(value = "/owner/{id}")
    public String editOwner(@PathVariable Integer id, Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("owner", service.getById(id));
        model.addAttribute("title", "Изменение данных клиента");
    //   model.addAttribute("сфкы", service.getRepairs(id));
        return "owner";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteOwner(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:../";
    }
}
