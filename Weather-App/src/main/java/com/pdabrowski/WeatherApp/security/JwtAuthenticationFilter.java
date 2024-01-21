package com.pdabrowski.WeatherApp.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pdabrowski.WeatherApp.dao.AccountDAO;
import com.pdabrowski.WeatherApp.entity.Account;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                token = token.substring(7);
                String username = jwtUtil.validateTokenAndRetrieveSubject(token);
                if (username != null && !username.isEmpty()) {
                    List<String> roles = jwtUtil.getRolesFromToken(token);
                    if (!roles.isEmpty()) {
                        List<GrantedAuthority> authorities = roles.stream()
                                .map(role -> new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role))
                                .collect(Collectors.toList());
                        Authentication authentication = new JwtAuthenticationToken(username, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Username: " + username);
                        System.out.println("Roles: " + authorities);
                    } else {
                        System.out.println("No roles found in token.");
                    }
                } else {
                    System.out.println("Username is null or empty.");
                }
            } catch (Exception e) {
                System.out.println("Error during token validation or role extraction: " + e.getMessage());
                SecurityContextHolder.clearContext();
            }
        } else {
            System.out.println("No Authorization token found.");
        }
        filterChain.doFilter(request, response);
    }
}