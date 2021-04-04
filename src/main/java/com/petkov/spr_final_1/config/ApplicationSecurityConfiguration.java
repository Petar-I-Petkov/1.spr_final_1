package com.petkov.spr_final_1.config;

import com.petkov.spr_final_1.service.impl.AppUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;


    public ApplicationSecurityConfiguration(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("/**").permitAll() //todo - change security setting
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/users/login-error");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserService)
                .passwordEncoder(passwordEncoder);
    }
}
