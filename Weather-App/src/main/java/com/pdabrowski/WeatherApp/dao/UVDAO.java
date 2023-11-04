package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.UV;
import java.util.List;
import java.util.Optional;
public interface UVDAO {

    UV save(UV uv);
    Optional<UV> findById(int id);
    List<UV> findAll();
    void delete(UV uv);



}
