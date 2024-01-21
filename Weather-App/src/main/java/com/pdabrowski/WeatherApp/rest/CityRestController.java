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
//@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{cityId}")
    public City getCityById(@PathVariable String cityId){
        System.out.println("city " + cityId);
        return cityService.getCityById(Integer.parseInt(cityId)).orElse(null);
    }

    @PostMapping("/cities")
    public City addCity(@RequestBody Map<String, String> data){
//        System.out.println(data);
//        Region existingRegion = regionService.getRegionById(Integer.valueOf(data.get("regionId")));
//
//        City newCity = new City();
//        newCity.setCityName(data.get("cityName"));
//
//        existingRegion.addCity(newCity);
//
//        City dbCity = cityService.saveCity(newCity);
//
////        regionService.saveRegion(existingRegion);
//
//        return dbCity;

        Region existingRegion = regionService.getRegionById(Integer.valueOf(data.get("regionId")));

        City newCity = new City();
        newCity.setCityName(data.get("cityName"));

        existingRegion.addCity(newCity);

        City db = cityService.saveCity(newCity);
        regionService.saveRegion(existingRegion);

        return db;
    }

    @DeleteMapping("/{cityId}")
    public void deleteCity(@PathVariable String cityId){

        City existingCity = this.cityService.getCityById(Integer.parseInt(cityId)).orElse(null);


        this.cityService.deleteCity(existingCity);
    }

}
