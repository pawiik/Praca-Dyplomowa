package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.UVDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UVServiceImplementation implements UVService{

    UVDAO uvdao;

    @Autowired
    public UVServiceImplementation(UVDAO uvdao){
        this.uvdao = uvdao;
    }

}
