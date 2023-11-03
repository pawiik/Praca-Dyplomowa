package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO{
    City save(City city);
    Optional<City> findById(int id);
    List<City> findAll();
    void delete(City city);
}
