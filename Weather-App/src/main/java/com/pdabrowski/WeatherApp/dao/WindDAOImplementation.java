package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Wind;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
@Repository
public class WindDAOImplementation implements WindDAO{

    EntityManager entityManager;

    @Autowired
    public WindDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Wind wind) {
        entityManager.persist(wind);
    }

    @Override
    public Optional<Wind> findById(int id) {
        return Optional.ofNullable(entityManager.find(Wind.class, id));
    }

    @Override
    public List<Wind> findAll() {
        return entityManager.createQuery("SELECT w FROM Wind w", Wind.class).getResultList();
    }

    @Override
    public void delete(Wind wind) {
        Wind mergedWind = entityManager.merge(wind);
        entityManager.remove(mergedWind);
    }}
