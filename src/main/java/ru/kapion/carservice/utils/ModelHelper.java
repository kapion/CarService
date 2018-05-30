package ru.kapion.carservice.utils;

import org.springframework.ui.Model;
import ru.kapion.carservice.security.user.UserAuthDto;

public class ModelHelper {
    public static void addUserAuthModel(Model model, UserAuthDto userAuthDto) {
        model.addAttribute("isAuth", userAuthDto.isAuth());
        model.addAttribute("authUserName", userAuthDto.getAuthName());
   }
}
