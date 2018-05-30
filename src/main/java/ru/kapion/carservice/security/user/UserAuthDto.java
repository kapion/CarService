package ru.kapion.carservice.security.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserAuthDto {
    private boolean isAuth;
    private String authName;

    public void clearAuth() {
        isAuth = false;
        authName = "";
    }
}
