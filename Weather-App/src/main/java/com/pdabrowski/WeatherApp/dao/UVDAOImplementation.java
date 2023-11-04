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

    @Override
    public UV save(UV uv) {
        return entityManager.merge(uv);
    }

    @Override
    public Optional<UV> findById(int id) {
        return Optional.ofNullable(entityManager.find(UV.class, id));
    }

    @Override
    public List<UV> findAll() {
        return entityManager.createQuery("from UV", UV.class).getResultList();
    }

    @Override
    public void delete(UV uv) {
        UV mergedUV = entityManager.merge(uv);
        entityManager.remove(mergedUV);
    }
}
