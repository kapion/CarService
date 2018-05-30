package ru.kapion.carservice.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.kapion.carservice.security.user.UserAuthDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Autowired
    UserAuthDto userAuthDto;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // передаем признак авторизации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userAuthDto.setAuth(authentication.isAuthenticated());
            userAuthDto.setAuthName(authentication.getName());
        }else {
            userAuthDto.clearAuth();
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
