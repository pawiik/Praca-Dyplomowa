package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.Humidity;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface HumidityDAO {

    Humidity save(Humidity humidity);
    Optional<Humidity> findById(int id);
    List<Humidity> findAll();
    void delete(Humidity humidity);
    Optional<List<Humidity>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException;

    Optional<List<Humidity>> findByTimePeriod(Instant startTime, Instant endTime, Integer regionId);

    Optional<Humidity> getLast(Integer cityId);

}
