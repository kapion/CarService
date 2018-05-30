package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Repair;
import ru.kapion.carservice.security.user.UserAuthDto;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.RepairService;
import ru.kapion.carservice.utils.ModelHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;


@Controller
@RequestMapping("/")
public class RepairsController  {

    @Autowired
    private RepairService repService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserAuthDto userAuthDto;

    @RequestMapping(value = "repairs")
    public String repsPage(Model model) {
        model.addAttribute("repairs", repService.getAll());
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "repairs";
    }

    @GetMapping(value = "cars/car/{id}/enroll")
    public String enroll(@PathVariable Integer id,Model model) {
        ObjectError objectError = new ObjectError("","" );
        Repair repair =  new Repair();
        repair.setDate(LocalDate.now());
        repair.setTime(LocalTime.now().plusHours(1).withMinute(0));
        repair.setCar(carService.getById(id));
        repair.setActive(true);
        model.addAttribute("repair",repair);
        model.addAttribute("title", "Запись на ремонт");
        model.addAttribute("objectError",objectError);
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "enroll";
    }

    @PostMapping(value = "cars/car/{carId}/enroll")
    public synchronized String saveEnroll(@PathVariable Integer carId,
            @ModelAttribute("repair") Repair repair, BindingResult result, Model model ) {
        repair.setCar(carService.getById(carId));
        // если Id пустой, то это новая запись о ремонте
        if (repair.getId() == null
              && repService.isExistRepair(repair.getDate(),repair.getTime())) {

            ObjectError objectError = new ObjectError("time", //поле, которое будет подсвечено
                    "Это время ремонта уже занято, выберите другое время.");
            result.addError(objectError);
           // пришлось изобретать велосипед, потому что Thymeleaf вылидация .hasErrors не работает
            //<p th:if="${#fields.hasErrors('time')}" th:errors="{*time}">Name Error</p>
            model.addAttribute("title", "Запись на ремонт");
            model.addAttribute("objectError",objectError);
            ModelHelper.addUserAuthModel(model,userAuthDto);
           // т.к. была ошибка заново вызываем форму
            return "enroll";
        }
        repService.save(repair);
        return "redirect:/repairs";
    }


    @PostMapping(value = "repairs/repair/save")
    public String saveRepair( @ModelAttribute("repair") Repair repair) {
        repair.setCar(carService.getById(repair.getCar().getId()));
        repService.save(repair);
        return "redirect:../";
    }


    @PostMapping(value = "repairs/repair/finish")
    public String saveRepairAndClose(@ModelAttribute("repair") Repair repair) {
        repair.setCar(carService.getById(repair.getCar().getId()));
        repair.setActive(false);
        repService.save(repair);
        return "redirect:../";
    }

    @GetMapping(value = "repairs/repair/{id}")
    public String editRepair(@PathVariable Integer id, Model model) {
        Repair repair = repService.getById(id);
        model.addAttribute("repair", repair );
        if (repair.getActive()) {
            model.addAttribute("title", "Изменение данных записи на ремонт");
        }else {
            model.addAttribute("title", "Обслуживание завершено");
        }
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "repair";
    }

    @PostMapping(value = "repairs/del/{id}")
    public String deleteCar(@PathVariable Integer id) {
        repService.delete(id);
        return "redirect:../";
    }
}
