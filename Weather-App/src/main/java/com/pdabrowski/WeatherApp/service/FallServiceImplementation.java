package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.FallDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FallServiceImplementation implements FallService{

    FallDAO fallDAO;

    @Autowired
    public FallServiceImplementation(FallDAO fallDAO){
        this.fallDAO = fallDAO;
    }

}
