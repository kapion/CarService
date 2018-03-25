package ru.kapion.carservice.configuration;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

public class H2ServerConfiguration2 {
//    @Bean  //для localhost
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/h2admin/*");
//        return registrationBean;
//    }

    @Bean //для web
    @ConditionalOnExpression("${h2.web.enabled:true}")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers").start();
    }


}
