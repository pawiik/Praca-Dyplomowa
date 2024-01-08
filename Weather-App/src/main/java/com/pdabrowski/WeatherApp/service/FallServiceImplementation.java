package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.FallDAO;
import com.pdabrowski.WeatherApp.entity.Fall;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class FallServiceImplementation implements FallService{

    FallDAO fallDao;

    @Autowired
    public FallServiceImplementation(FallDAO fallDAO){
        this.fallDao = fallDAO;
    }

    @Override
    @Transactional
    public Fall saveFall(Fall fall) {
        return fallDao.save(fall);
    }

    @Override
    @Transactional
    public Optional<Fall> getFallById(int id) {
        return fallDao.findById(id);
    }

    @Override
    @Transactional
    public List<Fall> getAllFalls() {
        return fallDao.findAll();
    }

    @Override
    @Transactional
    public void deleteFall(Fall fall) {
        fallDao.delete(fall);
    }

    @Override
    public Optional<List<Fall>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        return this.fallDao.findByTimePeriod(startTime, endTime, regionId);
    }

    @Override
    public Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException {

        List<Fall> falls = this.fallDao.getAllFromCityDay(cityId, day).orElse(null);

        Map<Integer, Double> sumOfFallsPerHour = new HashMap<>();
        Map<Integer, Integer> countOfFallsPerHour = new HashMap<>();

        assert falls != null;
        for (Fall fall : falls) {
            Calendar calendar = Calendar.getInstance();
            Date dateFromInstant = Date.from(fall.getTime());
            calendar.setTime(dateFromInstant);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            sumOfFallsPerHour.merge(hour, fall.getTemperature(), Double::sum);
            countOfFallsPerHour.merge(hour, 1, Integer::sum);
        }

        Map<Integer, Double> averageFallsPerHour = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            if (countOfFallsPerHour.containsKey(i) && sumOfFallsPerHour.containsKey(i)) {
                double average = sumOfFallsPerHour.get(i) / countOfFallsPerHour.get(i);
                averageFallsPerHour.put(i, average);
            } else {
                averageFallsPerHour.put(i, null);
            }
        }

        return Optional.of(averageFallsPerHour);
    }
}
