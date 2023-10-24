package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Region;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDAOImplementation implements RegionDAO{

    private EntityManager entityManager;

    @Autowired
    public RegionDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Region theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public List<Region> findAll() {
        return null;
    }


}
