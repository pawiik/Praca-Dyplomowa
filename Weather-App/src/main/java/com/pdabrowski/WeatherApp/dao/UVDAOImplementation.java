package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.*;
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

    @Override
    @Transactional
    public Optional<List<UV>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException {

        String hql = "SELECT f FROM UV f " +
                "JOIN f.measurementStation ms " +
                "JOIN ms.city c " +
                "WHERE c.cityId = :cityId AND " +
                "      DATE(f.time) = :date " +
                "ORDER BY FUNCTION('hour', f.time), f.time";

        TypedQuery<UV> query = this.entityManager.createQuery(hql, UV.class);
        query.setParameter("cityId", cityId);
        query.setParameter("date", java.sql.Date.valueOf("2024-01-06"));

        List<UV> measurements = query.getResultList();

        return Optional.of(measurements);
    }

    @Override
    public Optional<List<UV>> findByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        String queryStr = "SELECT f FROM UV f " +
                "JOIN f.measurementStation s " +
                "JOIN s.city c " +
                "JOIN c.region r " +
                "WHERE f.time BETWEEN :startTime AND :endTime AND r.id = :regionId";


        String hql = "SELECT f FROM UV f " +
                "JOIN f.measurementStation ms " +
                "WHERE ms.city.id = :cityId";

        TypedQuery<UV> query = entityManager.createQuery(queryStr, UV.class);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        query.setParameter("regionId", regionId);

        System.out.println("query");
        System.out.println(query.getResultList());
        System.out.println("af");
        return Optional.ofNullable(entityManager.createQuery(hql, UV.class)
                .setParameter("cityId", regionId)
                .getResultList());
    }

    @Override
    public Optional<UV> getLast(Integer cityId) {

        UV lastMeasurement = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UV> criteriaQuery = criteriaBuilder.createQuery(UV.class);

            Root<UV> temperatureRoot = criteriaQuery.from(UV.class);
            Join<UV, MeasurementStation> stationJoin = temperatureRoot.join("measurementStation");
            Join<MeasurementStation, City> cityJoin = stationJoin.join("city");

            criteriaQuery.select(temperatureRoot)
                    .where(criteriaBuilder.equal(cityJoin.get("cityId"), cityId))
                    .orderBy(criteriaBuilder.desc(temperatureRoot.get("time")));

            List<UV> results = entityManager.createQuery(criteriaQuery)
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
