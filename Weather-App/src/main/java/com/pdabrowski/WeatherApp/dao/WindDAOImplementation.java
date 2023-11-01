package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Wind;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WindDAOImplementation implements WindDAO{

    EntityManager entityManager;

    @Autowired
    public WindDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Wind theWind) {

        entityManager.merge(theWind);

    }

    @Override
    public Optional<Wind> findById(int theId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Wind>> findAll() {
        return Optional.empty();
    }
}
