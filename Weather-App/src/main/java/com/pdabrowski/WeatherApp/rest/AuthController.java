package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.dao.AccountDAO;
import com.pdabrowski.WeatherApp.entity.*;
import com.pdabrowski.WeatherApp.security.JwtUtil;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.EmployeeService;
import com.pdabrowski.WeatherApp.service.MeasurementStationService;
import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountDAO accountRepository;

    private final UserService userService;
    private final CityService cityService;

    private final JdbcUserDetailsManager userDetailsManager;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final MeasurementStationService measurementStationService;
    private final EmployeeService employeeService;

    @Autowired
    public AuthController(EmployeeService employeeService, MeasurementStationService measurementStationService, AuthenticationManager authenticationManager, CityService cityService, PasswordEncoder passwordEncoder, AccountDAO accountDAO, UserService userService, UserDetailsManager userDetailsManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountDAO;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.cityService = cityService;
        this.userDetailsManager = (JdbcUserDetailsManager) userDetailsManager;
        this.jwtUtil = jwtUtil;
        this.measurementStationService = measurementStationService;
        this.employeeService = employeeService;
    }

    @PostMapping("/register")
//    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> registrationData) {
        System.out.println("in register method");
        City existingCity = cityService.getCityById(Integer.parseInt(registrationData.get("cityId"))).orElse(null);
        if (existingCity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found.");
        }

        String name = registrationData.get("name");
        String lastName = registrationData.get("lastName");
        String address = registrationData.get("address");
        String email = registrationData.get("emailAddress");
        String password = registrationData.get("password");

        UserDetails newUserDetails = org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        userDetailsManager.createUser(newUserDetails);

        Account savedAccount = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found after creation"));

        com.pdabrowski.WeatherApp.entity.User newUser = new com.pdabrowski.WeatherApp.entity.User();
        newUser.setUserId(savedAccount.getAccountId());
        newUser.setAccount(savedAccount);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setCity(existingCity);
        existingCity.addUser(newUser);

        userService.saveUser(newUser);

        List<String> roles = Collections.singletonList("ROLE_USER");
        String token = jwtUtil.generateToken(email, roles);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("emailAddress");
        String password = loginData.get("password");
        System.out.println(email);
        System.out.println(password);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtUtil.generateToken(email, roles);

            Map<String, Object> body = new HashMap<>();
            body.put("jwtToken", token);

//            User existingUser = userService.getUserById(email).orElse(null);
//            if (existingUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
//            }

            body.put("user", email);
            body.put("roles", roles);

            System.out.println(token);
            return ResponseEntity.ok().body(body);

        } catch (AuthenticationException e) {
            System.out.println("Authentication Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }
    }

    @PostMapping("/register/employee")
//    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> registerEmployee(@RequestBody Map<String, String> registrationData) {
        System.out.println("in register method");
        MeasurementStation existingStation = measurementStationService.getStationById(Integer.parseInt(registrationData.get("stationId"))).orElse(null);
        if (existingStation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Station not found.");
        }

        String name = registrationData.get("name");
        String lastName = registrationData.get("lastName");
        String address = registrationData.get("address");
        String email = registrationData.get("emailAddress");
        String phoneNumber = registrationData.get("phoneNumber");
        String password = registrationData.get("password");

        UserDetails newUserDetails = org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .roles("EMPLOYEE")
                .build();

        userDetailsManager.createUser(newUserDetails);

        Account savedAccount = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found after creation"));

        Employee employee = new Employee();
        employee.setEmployeeId(savedAccount.getAccountId());
        employee.setAccount(savedAccount);
        employee.setName(name);
        employee.setLastName(lastName);
        employee.setAddress(address);
        employee.setPhoneNumber(Integer.parseInt(phoneNumber));
        employee.setMeasurementStation(existingStation);
        existingStation.addEmployee(employee);

        employeeService.saveEmployee(employee);

        List<String> roles = Collections.singletonList("ROLE_EMPLOYEE");
        String token = jwtUtil.generateToken(email, roles);

        return ResponseEntity.ok(new JwtResponse(token));
    }


}



