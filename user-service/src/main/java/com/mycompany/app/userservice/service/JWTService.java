package com.mycompany.app.userservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {


    public String generateToken(Authentication authentication, String secretKey) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
