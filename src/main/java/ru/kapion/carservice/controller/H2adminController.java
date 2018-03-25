package ru.kapion.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("/h2admin")
public class H2adminController {
    @RequestMapping
    public String h2Page(HttpServletRequest request) throws IOException {
        String to = "8082";
        String redirect = request.getScheme() + "://" +
                request.getServerName() + ":" + to;
        return "redirect:"+redirect;
    }
}
