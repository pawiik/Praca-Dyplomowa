package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.Fall;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FallService {
    Fall saveFall(Fall fall);
    Optional<Fall> getFallById(int id);
    List<Fall> getAllFalls();
    void deleteFall(Fall fall);

    Optional<List<Fall>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId);

    Optional<Map<Integer, Double>> getByDay(String day, Integer cityId) throws ParseException;

    Optional<Fall> getLastFromCity(Integer cityId);

}
