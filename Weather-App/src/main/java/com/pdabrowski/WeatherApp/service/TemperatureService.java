package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.Temperature;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface TemperatureService {
    Temperature saveTemperature(Temperature temperature);
    Optional<Temperature> getTemperatureById(int id);
    List<Temperature> getAllTemperatures();
    void deleteTemperature(Temperature temperature);

    Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException;

    Optional<Temperature> getLastFromCity(Integer cityId);
}
