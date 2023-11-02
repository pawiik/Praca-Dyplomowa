package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.UV;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class UVDAOImplementation implements UVDAO{

    EntityManager entityManager;

    @Autowired
    public UVDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(UV theUV) {
        entityManager.merge(theUV);
    }
}
