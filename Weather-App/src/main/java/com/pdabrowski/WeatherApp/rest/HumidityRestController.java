package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humidity")
public class HumidityRestController {

    HumidityService humidityService;

    @Autowired
    public HumidityRestController(HumidityService humidityService){
        this.humidityService = humidityService;
    }

}
