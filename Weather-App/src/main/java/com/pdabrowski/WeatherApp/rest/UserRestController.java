package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    UserService userService;
    CityService cityService;

    @Autowired
    public UserRestController(UserService userService, CityService cityService){
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/")
    public User getUserById(@RequestBody Map<String, Integer> data){
        return userService.getUserById(String.valueOf(data.get("userId"))).orElse(null);
    }
    @PostMapping("/")
    public User addUser(@RequestBody Map<String, String> data){
        City existingCity = cityService.getCityById(Integer.parseInt(data.get("cityId"))).orElse(null);

        if(existingCity != null){
            User newUser = new User();
            newUser.setName(data.get("name"));
            newUser.setLastName(data.get("lastName"));
            newUser.setAddress(data.get("address"));
            newUser.setEmail(data.get("emailAddress"));
            newUser.setPhoneNumber(Integer.valueOf(data.get("phoneNumber")));

            existingCity.addUser(newUser);

            return userService.saveUser(newUser);
        }

        return null;
    }
}
