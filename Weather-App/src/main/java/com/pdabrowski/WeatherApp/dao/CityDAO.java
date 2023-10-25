package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;

import java.util.List;

public interface CityDAO {
    public City save(City theCity);

    public List<City> findAll();
}
