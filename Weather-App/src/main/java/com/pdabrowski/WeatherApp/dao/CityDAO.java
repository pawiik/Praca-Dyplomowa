package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityDAO{
    void save(City city);
    Optional<City> findById(int id);
    List<City> findAll();
    void delete(City city);
}
