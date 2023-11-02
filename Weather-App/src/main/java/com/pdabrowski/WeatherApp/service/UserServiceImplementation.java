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

    UserDAO userDao;

    @Autowired
    public UserServiceImplementation(UserDAO userDAO){
        this.userDao = userDAO;
    }
@Override
@Transactional
public void saveUser(User user) {
    userDao.save(user);
}

    @Override
    @Transactional
    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
