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
                .antMatchers("/repairs").permitAll()
                .antMatchers("/cars/addcar").hasRole("EDITOR")
                .antMatchers("/cars/car/*").hasRole("EDITOR")
                .antMatchers("/cars/del/*").hasRole("EDITOR")
                .antMatchers("/repairs/enroll/*").hasRole("EDITOR")
                .antMatchers("/repairs/repair/*").hasRole("EDITOR")
                .antMatchers("/repairs/del/*").hasRole("EDITOR")
                .antMatchers("/h2-console").hasRole("EDITOR")
                .and()
                //.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("EDITOR");
    }
}
