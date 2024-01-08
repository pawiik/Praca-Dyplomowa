package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.Temperature;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HumidityService {
    Humidity saveHumidity(Humidity humidity);
    Optional<Humidity> getHumidityById(int id);
    List<Humidity> getAllHumidities();
    void deleteHumidity(Humidity humidity);
    Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException;

    Optional<Humidity> getLastFromCity(Integer cityId);
}
