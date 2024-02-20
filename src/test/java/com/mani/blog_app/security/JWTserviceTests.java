package com.mani.blog_app.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTserviceTests {
    JWTService jwtService = new JWTService();
    @Test
    void  canCreateJWTFromUserId(){
            var jwt = jwtService.CreateJwt(1001l);
            assertNotNull(jwt);
    }
}
