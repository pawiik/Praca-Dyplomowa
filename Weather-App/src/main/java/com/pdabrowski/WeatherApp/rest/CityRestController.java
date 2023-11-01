package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityRestController {

    CityService cityService;

    @Autowired
    public CityRestController(CityService cityService){
        this.cityService = cityService;
    }

}
