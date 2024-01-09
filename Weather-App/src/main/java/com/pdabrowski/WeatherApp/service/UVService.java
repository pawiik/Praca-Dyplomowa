package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.UV;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface UVService {
    UV saveUV(UV uv);
    Optional<UV> getUVById(int id);
    List<UV> getAllUVs();
    void deleteUV(UV uv);

    Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException;

    Optional<UV> getLastFromCity(Integer cityId);
}
