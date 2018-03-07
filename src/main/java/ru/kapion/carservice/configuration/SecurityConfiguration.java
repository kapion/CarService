package ru.kapion.carservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Import(H2ServerConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity config) throws Exception {
        config
                .authorizeRequests()
                .antMatchers("/cars").permitAll()
                .antMatchers("/cars/addCar").hasRole("EDITOR")
                .antMatchers("/cars/car/*").hasRole("EDITOR")
                .antMatchers("/cars/del/*").hasRole("EDITOR")
                .and()
                .formLogin().loginPage("/cars/login").defaultSuccessUrl("/cars/editor").permitAll()
                .and()
                .logout().logoutUrl("/cars/logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("EDITOR");
    }
}
