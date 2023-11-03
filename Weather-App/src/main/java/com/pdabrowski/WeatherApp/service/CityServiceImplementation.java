package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.CityDAO;
import com.pdabrowski.WeatherApp.entity.City;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImplementation implements CityService{

    CityDAO cityDao;

    @Autowired
    public CityServiceImplementation(CityDAO cityDAO){
        this.cityDao = cityDAO;
    }

    @Override
    @Transactional
    public City saveCity(City city) {
        return cityDao.save(city);
    }

    @Override
    @Transactional
    public Optional<City> getCityById(int id) {
        return cityDao.findById(id);
    }

    @Override
    @Transactional
    public List<City> getAllCities() {
        return cityDao.findAll();
    }

    @Override
    @Transactional
    public void deleteCity(City city) {
        cityDao.delete(city);
    }
}
