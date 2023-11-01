package com.pdabrowski.WeatherApp.rest;

import com.pdabrowski.WeatherApp.service.FallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fall")
public class FallRestController {

    FallService fallService;

    @Autowired
    public FallRestController(FallService fallService){
        this.fallService = fallService;
    }

}
