package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.entity.Wind;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface WindService {
    Wind saveWind(Wind wind);
    Optional<Wind> getWindById(int id);
    List<Wind> getAllWinds();
    void deleteWind(Wind wind);

    Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException;

    Optional<Wind> getLastFromCity(Integer cityId);
}
