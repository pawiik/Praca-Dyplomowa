package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureRestController {

    TemperatureService temperatureService;

    @Autowired
    public TemperatureRestController(TemperatureService temperatureService){
        this.temperatureService = temperatureService;
    }

}
