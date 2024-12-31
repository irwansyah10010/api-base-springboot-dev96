package com.lawencon.bootcamptest.config;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.lawencon.bootcamptest.business.user.service.UserService;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserService userService) throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userService)
                    .passwordEncoder(passwordEncoder())
                    .and().build();
    }

    @Bean
    public List<RequestMatcher> matchers(){
        List<RequestMatcher> requestMatchers = new ArrayList<>();

        requestMatchers.add(new AntPathRequestMatcher("/**/",HttpMethod.GET.name()));

        return requestMatchers;
    }

    @Bean
    public WebSecurityCustomizer customizer(HttpSecurity httpSecurity){
        return web -> matchers().forEach(t -> web.ignoring());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .cors()
                .and().build();
    }
}
