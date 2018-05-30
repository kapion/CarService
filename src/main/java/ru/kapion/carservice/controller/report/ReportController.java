package ru.kapion.carservice.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kapion.carservice.security.user.UserAuthDto;
import ru.kapion.carservice.service.RepairService;
import ru.kapion.carservice.service.report.ReportService;
import ru.kapion.carservice.utils.ModelHelper;


@Controller
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService service;

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserAuthDto userAuthDto;

    @RequestMapping
    public String reportPage(Model model) {
        model.addAttribute("summa", repairService.getAllSum());
        model.addAttribute("report", service.getReport());
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "report";
    }





}
