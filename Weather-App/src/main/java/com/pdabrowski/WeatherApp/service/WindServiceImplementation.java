package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.WindDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class WindServiceImplementation implements WindService{

    WindDAO windDAO;

    @Autowired
    public WindServiceImplementation(WindDAO windDAO){
        this.windDAO = windDAO;
    }

}
