package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImplementation implements UserDAO{

    EntityManager entityManager;

    @Autowired
    public UserDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User save(User theUser) {
         return entityManager.merge(theUser);
    }
}
