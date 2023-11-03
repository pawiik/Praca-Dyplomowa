//package com.pdabrowski.WeatherApp;
//import com.pdabrowski.WeatherApp.dao.CityDAO;
//import com.pdabrowski.WeatherApp.dao.RegionDAO;
//import com.pdabrowski.WeatherApp.dao.UserDAO;
//import com.pdabrowski.WeatherApp.entity.*;
//import com.pdabrowski.WeatherApp.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class AppRunner implements CommandLineRunner {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RegionService regionService;
//
//    @Autowired
//    private CityService cityService;
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Autowired
//    private FallService fallService;
//
//    @Autowired
//    private HumidityService humidityService;
//    @Autowired
//    private MeasurementStationService measurementStationService;
//
//    @Autowired
//    private TemperatureService temperatureService;
//
//    @Autowired
//    private UVService uvService;
//
//    @Autowired
//    private WindService windService;
//
//
//    @Autowired
//    RegionDAO regionDAO;
//
//    @Autowired
//    CityDAO cityDAO;
//
//    @Autowired
//    UserDAO userDAO;
//
//    @Override
//    public void run(String... args) throws Exception {
//
////        regionDAO.deleteAll();
////
////        Region region1 = new Region();
////        region1.setName("Region 1");
////        regionService.saveRegion(region1);
////
////        Region region2 = new Region();
////        region2.setName("Region 2");
////        regionService.saveRegion(region2);
////
////        // Create Cities and associate them with Regions
////        City city1 = new City();
////        city1.setCityName("City 1");
////        city1.setRegion(region1); // Set the region for city1
////        cityService.saveCity(city1);
////
////        City city2 = new City();
////        city2.setCityName("City 2");
////        city2.setRegion(region2); // Set the region for city2
////        cityService.saveCity(city2);
////
////        // Create Users and associate them with City
////        User user1 = new User();
////        // ... set properties of user1 ...
////        city1.addUser(user1);
////        user1.addRegion(region1); // Associate user1 with region1
////        userService.saveUser(user1);
////
////        User user2 = new User();
////        // ... set properties of user2 ...
////        city2.addUser(user2);
////        user2.addRegion(region2); // Associate user2 with region2
////        userService.saveUser(user2);
//
////        MeasurementStation measurementStation = new MeasurementStation();
////        City city = cityService.getCityById(10).orElse(null);
////        if(city != null){
////            measurementStation.setCity(city);
////            measurementStation.setAddress("super adres");
////            measurementStation.setRegionId(23);
////            measurementStationService.saveStation(measurementStation);
////        }
//
////        MeasurementStation existingStation = measurementStationService.getStationById(2).orElse(null);
////        if(existingStation != null){
////
////            Fall newFall = new Fall();
////            newFall.setTemperature(1);
////            newFall.setTime(1);
////
////            existingStation.addFall(newFall);
////            fallService.saveFall(newFall);
////
////            Humidity newHumidity = new Humidity();
////            newHumidity.setTemperature(1);
////            newHumidity.setTime(1);
////
////            existingStation.addHumidity(newHumidity);
////            humidityService.saveHumidity(newHumidity);
////
////            Temperature newTemperature = new Temperature();
////            newTemperature.setTemperature(1);
////            newTemperature.setTime(1);
////
////            existingStation.addTemperature(newTemperature);
////            temperatureService.saveTemperature(newTemperature);
////
////            UV newUV = new UV();
////            newUV.setTemperature(1);
////            newUV.setTime(1);
////
////            existingStation.addUV(newUV);
////            uvService.saveUV(newUV);
////
////            Wind newWind = new Wind();
////            newWind.setTemperature(1);
////            newWind.setTime(1);
////
////            existingStation.addWind(newWind);
////            windService.saveWind(newWind);
////        }
//
//
//
//}}