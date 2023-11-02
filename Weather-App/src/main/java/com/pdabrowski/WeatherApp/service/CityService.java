package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.City;

public interface CityService {

    public City createCity(String name);
    public void addCity(City city);
}
