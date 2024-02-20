package com.mani.blog_app.security;

import com.mani.blog_app.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    private  JWTService jwtService;
    private UsersService userService;

    public AppSecurityConfig(JWTService jwtService, UsersService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests().
                requestMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/articles", "/articles/*").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}