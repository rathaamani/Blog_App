package com.mani.blog_app.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenicationFilter extends AuthenticationFilter {

    private JWTAuthenticationManager jwtAuthenticationManager;
    public JWTAuthenicationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager,new JWTAuthenicationConvertor());

        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }

}
