package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.UV;
import com.pdabrowski.WeatherApp.entity.Wind;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.Instant;
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
    public Wind save(Wind wind) {
        return entityManager.merge(wind);
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
    }
    @Override
    @Transactional
    public Optional<List<Wind>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException {

        String hql = "SELECT f FROM Wind f " +
                "JOIN f.measurementStation ms " +
                "JOIN ms.city c " +
                "WHERE c.cityId = :cityId AND " +
                "      DATE(f.time) = :date " +
                "ORDER BY FUNCTION('hour', f.time), f.time";

        TypedQuery<Wind> query = this.entityManager.createQuery(hql, Wind.class);
        query.setParameter("cityId", cityId);
        query.setParameter("date", java.sql.Date.valueOf("2024-01-06"));

        List<Wind> measurements = query.getResultList();

        return Optional.of(measurements);
    }

    @Override
    public Optional<Wind> getLast(Integer cityId) {

        Wind lastMeasurement = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Wind> criteriaQuery = criteriaBuilder.createQuery(Wind.class);

            Root<Wind> temperatureRoot = criteriaQuery.from(Wind.class);
            Join<Wind, MeasurementStation> stationJoin = temperatureRoot.join("measurementStation");
            Join<MeasurementStation, City> cityJoin = stationJoin.join("city");

            criteriaQuery.select(temperatureRoot)
                    .where(criteriaBuilder.equal(cityJoin.get("cityId"), cityId))
                    .orderBy(criteriaBuilder.desc(temperatureRoot.get("time")));

            List<Wind> results = entityManager.createQuery(criteriaQuery)
                    .setMaxResults(1)
                    .getResultList();

            if (!results.isEmpty()) {
                lastMeasurement = results.get(0);
//                System.out.println("Last Fall from specified city: " + lastMeasurement.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(lastMeasurement);
    }
}
