package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.Temperature;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
public interface TemperatureDAO {

    Temperature save(Temperature temperature);
    Optional<Temperature> findById(int id);
    List<Temperature> findAll();
    void delete(Temperature temperature);

    Optional<List<Temperature>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException;

    Optional<Temperature> getLast(Integer cityId);
}
