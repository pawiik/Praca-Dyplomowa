package com.pdabrowski.WeatherApp;
import com.pdabrowski.WeatherApp.dao.CityDAO;
import com.pdabrowski.WeatherApp.dao.RegionDAO;
import com.pdabrowski.WeatherApp.dao.UserDAO;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.entity.User;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.RegionService;
import com.pdabrowski.WeatherApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;


    @Autowired
    RegionDAO regionDAO;

    @Autowired
    CityDAO cityDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public void run(String... args) throws Exception {

//        regionDAO.deleteAll();
//        cityDAO.deleteAll();
//        userDAO.deleteAll();
//        regionUserDAO.deleteAll();

        // Create and save regions
//        Region region11 = new Region();
//        region11.setName("Region 1");
//        region11 = regionService.addRegion(region11);
//
//        Region region21 = new Region();
//        region21.setName("Region 2");
//        region21 = regionService.addRegion(region21);

        regionDAO.deleteAll();

        Region region1 = new Region();
        region1.setName("Region 1");
        regionService.saveRegion(region1);

        Region region2 = new Region();
        region2.setName("Region 2");
        regionService.saveRegion(region2);

        // Create Cities and associate them with Regions
        City city1 = new City();
        city1.setCityName("City 1");
        city1.setRegion(region1); // Set the region for city1
        cityService.addCity(city1);

        City city2 = new City();
        city2.setCityName("City 2");
        city2.setRegion(region2); // Set the region for city2
        cityService.addCity(city2);

        // Create Users and associate them with City
        User user1 = new User();
        // ... set properties of user1 ...
        city1.addUser(user1);
        user1.addRegion(region1); // Associate user1 with region1
        userService.saveUser(user1);

        User user2 = new User();
        // ... set properties of user2 ...
        city2.addUser(user2);
        user2.addRegion(region2); // Associate user2 with region2
        userService.saveUser(user2);

}}