package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Fall;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FallDAO {

    Fall save(Fall fall);
    Optional<Fall> findById(int id);
    List<Fall> findAll();
    void delete(Fall fall);

    Optional<List<Fall>> findByTimePeriod(Instant startTime, Instant endTime, Integer regionId);

    Optional<List<Fall>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException;
}
