package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.UV;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
public interface UVDAO {

    UV save(UV uv);
    Optional<UV> findById(int id);
    List<UV> findAll();
    void delete(UV uv);

    Optional<List<UV>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException;

    Optional<UV> getLast(Integer cityId);

}
