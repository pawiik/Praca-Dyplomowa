package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.dao.AccountDAO;
import com.pdabrowski.WeatherApp.entity.Account;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;
import com.pdabrowski.WeatherApp.security.JwtUtil;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private final AccountDAO accountRepository;

    private final UserService userService;
    private final CityService cityService;


    private final JdbcUserDetailsManager userDetailsManager;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, CityService cityService, PasswordEncoder passwordEncoder, AccountDAO accountDAO, UserService userService, UserDetailsManager userDetailsManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
        this.accountRepository = accountDAO;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.cityService = cityService;
        this.userDetailsManager = (JdbcUserDetailsManager) userDetailsManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registrationData) {

        City existingCity = cityService.getCityById(Integer.parseInt(registrationData.get("cityId"))).orElse(null);
        if (existingCity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City not found.");
        }

        String name = registrationData.get("name");
        String lastName = registrationData.get("lastName");
        String address = registrationData.get("address");
        String email = registrationData.get("emailAddress");
        String password = registrationData.get("password");

        UserDetails newUserDetails = org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("USER") // This will translate to 'ROLE_USER' by default
                .build();

        userDetailsManager.createUser(newUserDetails);

        // Fetch the account_id from the database
        Account savedAccount = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Account not found after creation"));

        // Create the new User entity and associate it with the saved Account
        com.pdabrowski.WeatherApp.entity.User newUser = new com.pdabrowski.WeatherApp.entity.User();
        newUser.setUserId(savedAccount.getAccountId()); // Set the foreign key reference
        newUser.setAccount(savedAccount); // Associate the entities
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setCity(existingCity);
        existingCity.addUser(newUser);

        // Save the User entity
        userService.saveUser(newUser);

        // Generate the token for the new user
        String token = jwtUtil.generateToken(newUser.getEmail());

        // Return a response that includes the JWT token
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("emailAddress");
        String password = loginData.get("password");
        System.out.println(email);
        System.out.println(password);

        try {
            System.out.println("nie jeblo");
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = jwtUtil.generateToken(email);

            Map<String, Object> body = new HashMap<>();
            body.put("jwtToken", token);

            User existingUser = userService.getUserById(email).orElse(null);

            body.put("user", existingUser);

            return ResponseEntity.ok().body(body);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }
    }
}

