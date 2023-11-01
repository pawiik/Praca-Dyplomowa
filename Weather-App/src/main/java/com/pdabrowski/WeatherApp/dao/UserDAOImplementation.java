package com.pdabrowski.WeatherApp.dao;

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

}
