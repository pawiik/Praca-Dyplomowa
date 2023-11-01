package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    UserDAO userDAO;

    @Autowired
    public UserServiceImplementation(UserDAO userDAO){

    }

}
