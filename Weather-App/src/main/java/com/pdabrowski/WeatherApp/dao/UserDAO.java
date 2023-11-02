package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO {
    public User save(User theUser);
}
