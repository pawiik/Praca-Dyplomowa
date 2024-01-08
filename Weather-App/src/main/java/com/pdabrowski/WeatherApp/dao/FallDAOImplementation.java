package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.Employee;
import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class FallDAOImplementation implements FallDAO{

    EntityManager entityManager;


    @Autowired
    public FallDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Fall save(Fall fall) {
        return entityManager.merge(fall);
    }

    @Override
    public Optional<Fall> findById(int id) {
        return Optional.ofNullable(entityManager.find(Fall.class, id));
    }

    @Override
    public List<Fall> findAll() {
        return entityManager.createQuery("SELECT f FROM Fall f", Fall.class).getResultList();
    }



    @Override
    public void delete(Fall fall) {
        if (entityManager.contains(fall)) {
            entityManager.remove(fall);
        } else {
            entityManager.remove(entityManager.merge(fall));
        }
    }

    @Override
    @Transactional
    public Optional<List<Fall>> findByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        String queryStr = "SELECT f FROM Fall f " +
                "JOIN f.measurementStation s " +
                "JOIN s.city c " +
                "JOIN c.regionId r " +
//                "WHERE f.time BETWEEN :startTime AND :endTime " +
                "WHERE r.id = :regionId";

        String hql = "SELECT f FROM Fall f " +
                "JOIN f.measurementStation ms " +
                "WHERE ms.city.id = :cityId";

        TypedQuery<Fall> query = entityManager.createQuery(queryStr, Fall.class);
//        query.setParameter("startTime", startTime);
//        query.setParameter("endTime", endTime);
        query.setParameter("regionId", regionId);

        System.out.println("query");
        System.out.println(query.getResultList());
        System.out.println("af");
//        return Optional.of(query.getResultList());
        return Optional.ofNullable(entityManager.createQuery(hql, Fall.class)
                .setParameter("cityId", regionId)
                .getResultList());
//        return Optional.ofNullable(query.getResultList());
    }

    @Override
    @Transactional
    public Optional<List<Fall>> getAllFromCityDay(Integer cityId, Instant date) throws ParseException {

        String hql = "SELECT f FROM Fall f " +
                "JOIN f.measurementStation ms " +
                "JOIN ms.city c " +
                "WHERE c.cityId = :cityId AND " +
                "      DATE(f.time) = :date " +
                "ORDER BY FUNCTION('hour', f.time), f.time";

        TypedQuery<Fall> query = this.entityManager.createQuery(hql, Fall.class);
        query.setParameter("cityId", "10");
        query.setParameter("date", java.sql.Date.valueOf("2024-01-06"));

        List<Fall> falls = query.getResultList();
        for (Fall fall : falls) {
            System.out.println(fall);
        }
        System.out.println(falls);

        return Optional.of(falls);
    }

    @Override
    public Optional<Fall> getLast(Integer cityId) {

        Fall lastFall = null;

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Fall> criteriaQuery = criteriaBuilder.createQuery(Fall.class);

            Root<Fall> fallRoot = criteriaQuery.from(Fall.class);
            Join<Fall, MeasurementStation> stationJoin = fallRoot.join("measurementStation");
            Join<MeasurementStation, City> cityJoin = stationJoin.join("city");

            criteriaQuery.select(fallRoot)
                    .where(criteriaBuilder.equal(cityJoin.get("cityId"), cityId))
                    .orderBy(criteriaBuilder.desc(fallRoot.get("time")));

            List<Fall> results = entityManager.createQuery(criteriaQuery)
                    .setMaxResults(1)
                    .getResultList();

            if (!results.isEmpty()) {
                lastFall = results.get(0);
                System.out.println("Last Fall from specified city: " + lastFall.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(lastFall);
    }
}
