package com.pdabrowski.WeatherApp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public SecurityConfig(JwtUtil jwtUtil, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/alert/alerts", "/alert/{cityId}", "/city/cities", "/fall/day", "/humidity/day", "/temperature/day", "/uv/day", "/wind/day", "/fall/last", "/temperature/last", "/humidity/last", "/temperature/last", "/uv/last", "/wind/last", "city/{cityId}", "/region/regions").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/alert/", "/measurementStation/stations").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/user/{userId}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/employee/employees", "/employee/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/measurementStation/", "/region/", "/alert/", "/city/cities", "/fall/time", "/humidity/time", "temperature/time", "/uv/time", "/wind/time", "/fall/", "/humidity/", "/temperature/", "/uv/", "wind/").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/auth/register/employee", "/employee/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/measurementStation/", "/city/", "/region/", "/alert/").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.PUT, "/**", "user/").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/city/**", "/measurementStation/**", "/alert/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager theUserDetailManager = new JdbcUserDetailsManager(dataSource);

        theUserDetailManager.setUsersByUsernameQuery("select account_id, pw, active from accounts where account_id=?");
        theUserDetailManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        theUserDetailManager.setCreateUserSql("insert into accounts (account_id, pw, active) values (?,?,?)");
        theUserDetailManager.setUpdateUserSql("update accounts set pw = ?, active = ? where account_id = ?");
        theUserDetailManager.setDeleteUserSql("delete from accounts where account_id = ?");

        theUserDetailManager.setCreateAuthoritySql("insert into roles (user_id, role) values (?,?)");
        theUserDetailManager.setDeleteUserAuthoritiesSql("delete from roles where user_id = ?");
        theUserDetailManager.setChangePasswordSql("update accounts set pw = ? where account_id = ?");

        return theUserDetailManager;

    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}