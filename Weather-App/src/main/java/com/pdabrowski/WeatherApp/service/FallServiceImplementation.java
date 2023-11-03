package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.FallDAO;
import com.pdabrowski.WeatherApp.entity.Fall;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FallServiceImplementation implements FallService{

    FallDAO fallDao;

    @Autowired
    public FallServiceImplementation(FallDAO fallDAO){
        this.fallDao = fallDAO;
    }

    @Override
    @Transactional
    public Fall saveFall(Fall fall) {
        return fallDao.save(fall);
    }

    @Override
    @Transactional
    public Optional<Fall> getFallById(int id) {
        return fallDao.findById(id);
    }

    @Override
    @Transactional
    public List<Fall> getAllFalls() {
        return fallDao.findAll();
    }

    @Override
    @Transactional
    public void deleteFall(Fall fall) {
        fallDao.delete(fall);
    }
}
