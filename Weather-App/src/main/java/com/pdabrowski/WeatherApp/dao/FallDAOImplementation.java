package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Employee;
import com.pdabrowski.WeatherApp.entity.Fall;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FallDAOImplementation implements FallDAO{

    EntityManager entityManager;


    @Autowired
    public FallDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Fall theFall) {
        entityManager.merge(theFall);
    }
}
