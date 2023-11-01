package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.WindDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindServiceImplementation implements WindService{

    WindDAO windDAO;

    @Autowired
    public WindServiceImplementation(WindDAO windDAO){
        this.windDAO = windDAO;
    }

}
