package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

}
