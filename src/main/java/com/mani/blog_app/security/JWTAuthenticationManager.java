package com.mani.blog_app.security;

import com.mani.blog_app.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {
    private JWTService jwtService;
    private UsersService userService;

    public JWTAuthenticationManager(JWTService jwtService, UsersService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JWTAuthentication){

            var jwtAuthentication = (JWTAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId =  jwtService.retrieveUserId((String) jwt);
            var userEntity = userService.getUser(userId);

            jwtAuthentication.userEntity = userEntity;
            jwtAuthentication.setAuthenticated(true);

            return authentication;
        }
        throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
    }
}
