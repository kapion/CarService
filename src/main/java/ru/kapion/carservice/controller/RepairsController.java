package ru.kapion.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.model.Repair;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.RepairService;

import java.time.LocalDate;
import java.time.LocalTime;


@Controller
@RequestMapping("/repairs")
public class RepairsController implements SecurityCheck {

    @Autowired
    private RepairService repService;

    @Autowired
    private CarService carService;

    @RequestMapping
    public String repsPage(Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("repairs", repService.getAll());
        return "repairs";
    }

    @RequestMapping(value = "/enroll/{id}")
    public String enroll(@PathVariable Integer id,Model model) {
        Repair repair =  new Repair();
        repair.setDate(LocalDate.now());
        repair.setTime(LocalTime.now().plusHours(1).withMinute(0));
        repair.setCar(carService.getById(id));
        repair.setActive(true);
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("repair",repair);
        model.addAttribute("title", "Запись на ремонт");
        return "enroll";
    }

    @PostMapping(value = "/repair/save")
    public String saveRepair(@ModelAttribute("repair") Repair repair) {
        repair.setCar(carService.getById(repair.getCar().getId()));
        repService.save(repair);
        return "redirect:../";
    }

    @PostMapping(value = "/repair/finish")
    public String saveRepairAndClose(@ModelAttribute("repair") Repair repair) {
        repair.setCar(carService.getById(repair.getCar().getId()));
        repair.setActive(false);
        repService.save(repair);
        return "redirect:../";
    }

    @GetMapping(value = "/repair/{id}")
    public String editRepair(@PathVariable Integer id, Model model) {
        Repair repair = repService.getById(id);
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("repair", repair );
        if (repair.getActive()) {
            model.addAttribute("title", "Изменение данных записи на ремонт");
        }else {
            model.addAttribute("title", "Обслуживание завершено");
        }
        return "repair";
    }

    @PostMapping(value = "/del/{id}")
    public String deleteCar(@PathVariable Integer id) {
        repService.delete(id);
        return "redirect:../";
    }
}
