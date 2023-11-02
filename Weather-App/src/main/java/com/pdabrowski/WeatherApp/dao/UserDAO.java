package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface UserDAO {
    public User save(User theUser);
}
