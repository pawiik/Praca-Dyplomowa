package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.dao.AccountDAO;
import com.pdabrowski.WeatherApp.dao.UserDAO;
import com.pdabrowski.WeatherApp.entity.Account;
import com.pdabrowski.WeatherApp.entity.AccountType;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;
import com.pdabrowski.WeatherApp.security.JwtTokenUtil;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    private AuthenticationManager authenticationManager;
    private AccountDAO accountRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private CityService cityService;

    @Autowired
    public AuthController(CityService cityService, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, AccountDAO accountDAO, UserService userService) {
//        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountRepository = accountDAO;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.cityService = cityService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registrationData) {

        City existingCity = cityService.getCityById(Integer.parseInt(registrationData.get("cityId"))).orElse(null);

        String name = registrationData.get("name");
        String lastName = registrationData.get("lastName");
        String address = registrationData.get("address");
        String email = registrationData.get("emailAddress");
        String password = registrationData.get("password");

        User newUser = new User();
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setAddress(address);
        newUser.setEmail(email);

        password = passwordEncoder.encode(password);

        Account newAccount = new Account();
        newAccount.setId(email);
        newAccount.setPasswordHash(password);
        newAccount.setUser(newUser);
        newAccount.setAccountType(AccountType.user);
        assert existingCity != null;
        existingCity.addUser(newUser);
        userService.saveUser(newUser);
        accountRepository.save(newAccount);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("emailAddress");
            String password = loginData.get("password");

            // Authenticate the user
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password));

            Account account = accountRepository.findByEmail(username)
                    .orElseThrow(() -> new Exception("User not found with email: " + username));

            if (!passwordEncoder.matches(password, account.getPasswordHash())) {
                throw new Exception("Invalid password");
            }

            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + account.getAccountType().name()));

            String jwt = jwtTokenUtil.generateToken(account.getId(), authorities);
            String dec = jwtTokenUtil.getUsernameFromToken(jwt);
            System.out.println(dec);

            Map<String, Object> claims = new HashMap<>();
            claims.put("user", account.getUser());


//            String token = jwtTokenUtil.generateToken(userDetails, claims);

            Map<String, Object> data = new HashMap<>();
            data.put("jwtToken", jwt);
            data.put("user", claims);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON).body(data);
//                    .body(new JwtResponse(jwt));
        } catch (AuthenticationException e) {
            System.out.println("zesralo sie");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // DTO classes for RegistrationRequest, LoginRequest, and JwtResponse
    // should be defined to handle the request and response data
}}

// ... Other DTOs like RegistrationRequest and LoginRequest ...