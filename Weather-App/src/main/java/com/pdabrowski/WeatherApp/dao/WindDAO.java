package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.entity.Wind;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
public interface WindDAO {

    Wind save(Wind wind);
    Optional<Wind> findById(int id);
    List<Wind> findAll();
    void delete(Wind wind);

    Optional<List<Wind>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException;

    Optional<List<Wind>> findByTimePeriod(Instant startTime, Instant endTime, Integer regionId);

    Optional<Wind> getLast(Integer cityId);

}
