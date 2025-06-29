package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.Region;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class RegionDAOImplementation implements RegionDAO{

    private EntityManager entityManager;

    @Autowired
    public RegionDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Region save(Region theStudent) {
        return entityManager.merge(theStudent);
    }

    @Override
    public List<Region> findAll() {

        List<Region> regions = entityManager.createQuery("SELECT a FROM Region a", Region.class)
                .getResultList();

        return regions;
    }

    @Override
    public Optional<Region> findById(int theId) {

        Region theRegion = entityManager.find(Region.class, theId);

        return Optional.ofNullable(theRegion);
    }

    @Transactional
    @Override
    public void delete(Region region) {
        Region managedRegion = entityManager.find(Region.class, region.getRegionId());
        if (managedRegion != null) {
            entityManager.remove(managedRegion);
        }
    }


}
