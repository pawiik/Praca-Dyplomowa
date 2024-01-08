package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import com.pdabrowski.WeatherApp.entity.Temperature;
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
@Repository
public class HumidityDAOImplementation implements HumidityDAO{

    EntityManager entityManager;

    @Autowired
    public HumidityDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Humidity save(Humidity humidity) {
        return entityManager.merge(humidity);
    }

    @Override
    public Optional<Humidity> findById(int id) {
        return Optional.ofNullable(entityManager.find(Humidity.class, id));
    }

    @Override
    public List<Humidity> findAll() {
        return entityManager.createQuery("SELECT h FROM Humidity h", Humidity.class).getResultList();
    }

    @Override
    public void delete(Humidity humidity) {
        if (entityManager.contains(humidity)) {
            entityManager.remove(humidity);
        } else {
            entityManager.remove(entityManager.merge(humidity));
        }
    }

    @Override
    @Transactional
    public Optional<List<Humidity>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException {

        String hql = "SELECT f FROM Humidity f " +
                "JOIN f.measurementStation ms " +
                "JOIN ms.city c " +
                "WHERE c.cityId = :cityId AND " +
                "      DATE(f.time) = :date " +
                "ORDER BY FUNCTION('hour', f.time), f.time";

        TypedQuery<Humidity> query = this.entityManager.createQuery(hql, Humidity.class);
        query.setParameter("cityId", "10");
        query.setParameter("date", java.sql.Date.valueOf("2024-01-06"));

        List<Humidity> measurements = query.getResultList();
        for (Humidity fall : measurements) {
            System.out.println(fall);
        }
        System.out.println(measurements);

        return Optional.of(measurements);
    }

    @Override
    public Optional<Humidity> getLast(Integer cityId) {

        Humidity lastMeasurement = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Humidity> criteriaQuery = criteriaBuilder.createQuery(Humidity.class);

            Root<Humidity> temperatureRoot = criteriaQuery.from(Humidity.class);
            Join<Humidity, MeasurementStation> stationJoin = temperatureRoot.join("measurementStation");
            Join<MeasurementStation, City> cityJoin = stationJoin.join("city");

            criteriaQuery.select(temperatureRoot)
                    .where(criteriaBuilder.equal(cityJoin.get("cityId"), cityId))
                    .orderBy(criteriaBuilder.desc(temperatureRoot.get("time")));

            List<Humidity> results = entityManager.createQuery(criteriaQuery)
                    .setMaxResults(1)
                    .getResultList();

            if (!results.isEmpty()) {
                lastMeasurement = results.get(0);
                System.out.println("Last Fall from specified city: " + lastMeasurement.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(lastMeasurement);
    }
}
