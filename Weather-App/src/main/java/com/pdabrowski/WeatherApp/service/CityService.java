package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    void saveCity(City city);
    Optional<City> getCityById(int id);
    List<City> getAllCities();
    void deleteCity(City city);
}
