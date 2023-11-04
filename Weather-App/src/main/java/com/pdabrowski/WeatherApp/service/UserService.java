package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;
import java.util.List;
import java.util.Optional;
public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(Integer id);
    List<User> getAllUsers();
    void deleteUser(User user);
}
