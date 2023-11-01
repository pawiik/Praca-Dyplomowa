package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImplementation implements CityService{

    CityDAO cityDAO;

    @Autowired
    public CityServiceImplementation(CityDAO cityDAO){
        this.cityDAO = cityDAO;
    }
}
