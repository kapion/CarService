package ru.kapion.carservice.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.controller.SecurityCheck;
import ru.kapion.carservice.model.Car;
import ru.kapion.carservice.model.Owner;
import ru.kapion.carservice.service.CarService;
import ru.kapion.carservice.service.OwnerService;
import ru.kapion.carservice.service.RepairService;
import ru.kapion.carservice.service.report.ReportService;
import ru.kapion.carservice.utils.DicHelper;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/report")
public class ReportController implements SecurityCheck {


    @Autowired
    private ReportService service;

    @Autowired
    private RepairService repairService;



    @RequestMapping
    public String reportPage(Model model) {
        model.addAttribute("isAuth", isAuth());
        model.addAttribute("summa", repairService.getAllSum());
        model.addAttribute("report", service.getReport());
        return "report";
    }





}
