package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;

public interface UserService {

    public User createUser(String name, String lastName, Integer phoneNumber, String address, String email, City city);
    public User saveUser(User user);
    public User addUser(User user1);
}
