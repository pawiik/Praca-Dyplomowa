package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Region;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
public interface RegionDAO extends JpaRepository<Region, Integer> {

//    public void save(Region theRegion);
//
//    List<Region> findAll();
//
//    public Optional<Region> findById(int theId);

}
