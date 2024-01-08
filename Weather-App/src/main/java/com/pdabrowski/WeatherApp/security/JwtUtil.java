package com.pdabrowski.WeatherApp.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pdabrowski.WeatherApp.entity.AccountType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key";


    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 24 hours validity
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public String validateTokenAndRetrieveSubject(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
