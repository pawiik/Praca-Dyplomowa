package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wind")
public class WindRestController {

    WindService windService;

    @Autowired
    public WindRestController(WindService windService){
        this.windService = windService;
    }

}
