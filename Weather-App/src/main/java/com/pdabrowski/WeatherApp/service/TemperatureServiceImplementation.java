package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.TemperatureDAO;
import com.pdabrowski.WeatherApp.entity.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureServiceImplementation implements TemperatureService {

    TemperatureDAO temperatureDAO;

    @Autowired
    public TemperatureServiceImplementation(TemperatureDAO temperatureDAO){
        this.temperatureDAO = temperatureDAO;
    }

}
