package ru.kapion.carservice.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kapion.carservice.security.user.UserAuthDto;
import ru.kapion.carservice.service.RepairService;
import ru.kapion.carservice.service.report.ReportService;
import ru.kapion.carservice.utils.ModelHelper;


@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportService service;
    private RepairService repairService;
    private UserAuthDto userAuthDto;

    public ReportController(ReportService service, RepairService repairService, UserAuthDto userAuthDto) {
        this.service = service;
        this.repairService = repairService;
        this.userAuthDto = userAuthDto;
    }

    @RequestMapping
    public String reportPage(Model model) {
        model.addAttribute("summa", repairService.getAllSum());
        model.addAttribute("report", service.getReport());
        ModelHelper.addUserAuthModel(model,userAuthDto);
        return "report";
    }





}
