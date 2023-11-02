package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.UserDAO;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImplementation implements UserService{

//    UserDAO userDAO;
//
//    @Autowired
//    public UserServiceImplementation(UserDAO userDAO){
//        this.userDAO = userDAO;
//    }
@Transactional
public User saveUser(User user) {
    return userRepository.save(user);
}
    @Autowired
    private UserDAO userRepository; // assuming you have a UserRepository

    // Method to add a user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(String name, String lastName, Integer phoneNumber, String address, String email, City city) {
        return null;
    }
}
