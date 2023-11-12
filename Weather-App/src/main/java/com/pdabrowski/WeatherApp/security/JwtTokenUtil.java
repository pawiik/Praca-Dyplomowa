package com.pdabrowski.WeatherApp.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pdabrowski.WeatherApp.entity.AccountType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Collection;
import java.util.Date;


@Component
public class JwtTokenUtil {

    private static final String SECRET = "YourSecretKey"; // Use a strong secret key
    private static final long EXPIRATION_TIME = 900_000; // 15 minutes in milliseconds

//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expirationTime;

    // Method to generate a token
    public String generateToken(String username, Collection<? extends GrantedAuthority> accountType) {
        return JWT.create()
                .withSubject(username)
                .withClaim("role", accountType.toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
    }
    // Method to validate the token
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            //Invalid token
            return false;
        }
    }

    // Method to get the username from the token
    public String getUsernameFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    // Method to get the role from the token
    public AccountType getAccountTypeFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String role = jwt.getClaim("role").asString();
        return AccountType.valueOf(role);
    }

    // Method to resolve the token from the request header
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}