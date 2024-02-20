package com.mani.blog_app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String JWT_KEY = "34819f0f-3e7c-4b36-ad0c-f2bf10a97579";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String CreateJwt(Long userId) {
       return JWT.create()
               .withSubject(userId.toString())
               .withIssuedAt(new Date())
                //.withExpiresAt() // TODO : setup and Expiry Parameter
                .sign(algorithm);
   }

    public Long retrieveUserId(String jwtString) {
        var decodedJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodedJWT.getSubject());
        return userId;
    }
}
