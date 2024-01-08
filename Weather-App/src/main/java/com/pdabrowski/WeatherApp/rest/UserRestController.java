package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.entity.User;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.RegionService;
import com.pdabrowski.WeatherApp.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    UserService userService;
    CityService cityService;

    RegionService regionService;

    @Autowired
    public UserRestController(UserService userService, CityService cityService, RegionService regionService){
        this.userService = userService;
        this.cityService = cityService;
        this.regionService = regionService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        System.out.println(userId);
        return userService.getUserById(userId).orElse(null);
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

    @PutMapping("/")
    public User updateUserInformation(@RequestBody Map<String, String> data){
        User dbUser = userService.getUserById(data.get("userId")).orElse(null);
        System.out.println("updating user");
        assert dbUser != null;
        dbUser.setName(data.get("name"));
        dbUser.setLastName(data.get("lastName"));
        dbUser.setAddress(data.get("address"));
        System.out.println();
        dbUser.setPhoneNumber(Integer.parseInt(data.get("phoneNumber")));
        dbUser.setEmail("emailAddress");

        User updatedUser = userService.saveUser(dbUser);

        return updatedUser;
    }

    @PutMapping("/region")
    public User addRegionToUser(@RequestBody Map<String, String> data){
        User dbUser = userService.getUserById(data.get("userId")).orElse(null);
        Region dbRegion = regionService.getRegionById(Integer.valueOf(data.get("regionId")));

        User updatedUser = userService.addRegionToUser(dbRegion, dbUser);

        return updatedUser;
    }

    @GetMapping("/alert/{userId}")
    public List<Alert> getUserAlerts(@PathVariable String userId){
        User dbUser = userService.getUserById(userId).orElse(null);
        List<Alert> userAlerts = userService.getUserAlerts(dbUser);
        System.out.println("alerts");
        System.out.println(userAlerts);
        return userAlerts;
    }
}
