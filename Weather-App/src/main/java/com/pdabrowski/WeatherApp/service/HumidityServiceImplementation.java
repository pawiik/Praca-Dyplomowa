package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.HumidityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class HumidityServiceImplementation implements HumidityService{

    HumidityDAO humidityDAO;

    @Autowired
    public HumidityServiceImplementation(HumidityDAO humidityDAO){
        this.humidityDAO = humidityDAO;
    }

}
