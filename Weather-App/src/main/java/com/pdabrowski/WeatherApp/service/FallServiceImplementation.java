package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.FallDAO;
import org.springframework.stereotype.Service;

@Service
public class FallServiceImplementation implements FallService{

    FallDAO fallDAO;

    public FallServiceImplementation(FallDAO fallDAO){
        this.fallDAO = fallDAO;
    }

}
