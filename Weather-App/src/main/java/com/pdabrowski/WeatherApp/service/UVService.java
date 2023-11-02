package com.pdabrowski.WeatherApp.service;
import com.pdabrowski.WeatherApp.entity.UV;

import java.util.List;
import java.util.Optional;
public interface UVService {
    void saveUV(UV uv);
    Optional<UV> getUVById(int id);
    List<UV> getAllUVs();
    void deleteUV(UV uv);
}
