package ru.kapion.carservice.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SecurityCheck{
    default boolean isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // String currentUserName = authentication.getName();
            return authentication.isAuthenticated();
        } else return false;
     }

    default String getAuthName() {
            String currentUserName = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                 currentUserName = authentication.getName();

            }
            return currentUserName;

    }

}
