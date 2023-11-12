package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.service.CityService;
import com.pdabrowski.WeatherApp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityRestController {

    CityService cityService;
    RegionService regionService;

    @Autowired
    public CityRestController(CityService cityService, RegionService regionService){
        this.cityService = cityService;
        this.regionService = regionService;
    }

    @GetMapping("/cities")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/")
    public City getCityById(@RequestParam Map<String, String> data){
        return cityService.getCityById(Integer.parseInt(data.get("cityId"))).orElse(null);
    }

    @PostMapping("/")
    public City addCity(@RequestBody Map<String, String> data){
        Region existingRegion = regionService.getRegionById(Integer.valueOf(data.get("regionId")));

        City newCity = new City();
        newCity.setCityName(data.get("cityName"));

        existingRegion.addCity(newCity);

        City dbCity = cityService.saveCity(newCity);

        return dbCity;
    }

}
