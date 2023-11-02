package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.CityDAO;
import com.pdabrowski.WeatherApp.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImplementation implements CityService{

    CityDAO cityDAO;

    @Autowired
    public CityServiceImplementation(CityDAO cityDAO){
        this.cityDAO = cityDAO;
    }

    @Override
    public City createCity(String name) {
        City city = new City();
        city.setCityName(name);
        return cityDAO.save(city);
    }

    @Override
    public void addCity(City city) {
        cityDAO.save(city);
    }
}
