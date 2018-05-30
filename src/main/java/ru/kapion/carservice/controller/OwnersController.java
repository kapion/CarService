package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/clients")
public class OwnersController {

    @Autowired
    private OwnerService service;

    @Autowired
    private UserAuthDto userAuthDto;

    @RequestMapping
    public String ownersPage(Model model) {
        model.addAttribute("owners", service.getAll(false));
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "clients";
    }

    @RequestMapping(value = "/add")
    public String createOwner(Model model) {
        model.addAttribute("owner", new Owner());
        model.addAttribute("title", "Добавление клиента");
        model.addAttribute("errorMessage", "");
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "addowner";
    }

    @RequestMapping(value = "/add-and-check-owner", method = RequestMethod.POST)
    public synchronized ModelAndView checkUser(@Valid @ModelAttribute("owner") Owner owner,
                                  HttpSession httpSession,
                                 // BindingResult bindingResult,
                                  Model model, RedirectAttributes redirectAttributes) throws InterruptedException {
        //тут испльзуем Post-Redirect-Get
        ModelAndView modelAndView = new ModelAndView();
        if (!service.isExist(owner)) {
            service.save(owner);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сохранено");
            RedirectView redirectView = new RedirectView("../clients");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
        } else {
            //выводим ту же форму с отображением ошибки
            model.addAttribute("errorMessage", "Клиент с таким номером телефона уже существует!");
            model.addAttribute("title", "Добавление клиента");
            modelAndView.setViewName("addowner");
        }

        return modelAndView;
    }



    @PostMapping(value = "/owner/save")
    public String saveOwner(@ModelAttribute("owner") Owner owner) {
        service.save(owner);
        return "redirect:../";
    }

    @GetMapping(value = "/owner/{id}")
    public String editOwner(@PathVariable Integer id, Model model) {
        model.addAttribute("owner", service.getById(id));
        model.addAttribute("title", "Изменение данных клиента");
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "owner";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteOwner(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:../";
    }
}
