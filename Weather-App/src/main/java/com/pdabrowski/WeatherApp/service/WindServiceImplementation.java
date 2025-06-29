package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.WindDAO;
import com.pdabrowski.WeatherApp.entity.Wind;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class WindServiceImplementation implements WindService{

    WindDAO windDao;

    @Autowired
    public WindServiceImplementation(WindDAO windDAO){
        this.windDao = windDAO;
    }
    @Override
    @Transactional
    public Wind saveWind(Wind wind) {
        return windDao.save(wind);
    }

    @Override
    @Transactional
    public Optional<Wind> getWindById(int id) {
        return windDao.findById(id);
    }

    @Override
    @Transactional
    public List<Wind> getAllWinds() {
        return windDao.findAll();
    }

    @Override
    @Transactional
    public void deleteWind(Wind wind) {
        windDao.delete(wind);
    }

    @Override
    public Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException {

        List<Wind> measurements = this.windDao.getAllFromCityDay(cityId, day).orElse(null);

        Map<Integer, Double> sumOfMeasurementsPerHour = new HashMap<>();
        Map<Integer, Integer> countOfMeasurementsPerHour = new HashMap<>();

        assert measurements != null;
        for (Wind measurement : measurements) {
            Calendar calendar = Calendar.getInstance();
            Date dateFromInstant = Date.from(measurement.getTime());
            calendar.setTime(dateFromInstant);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            sumOfMeasurementsPerHour.merge(hour, measurement.getWind(), Double::sum);
            countOfMeasurementsPerHour.merge(hour, 1, Integer::sum);
        }

        Map<Integer, Double> averageUvPerHour = new HashMap<>();

        for (int i = 0; i < 24; i++) {
            if (countOfMeasurementsPerHour.containsKey(i) && sumOfMeasurementsPerHour.containsKey(i)) {
                double sum = sumOfMeasurementsPerHour.get(i);
                double count = countOfMeasurementsPerHour.get(i);
                BigDecimal average = BigDecimal.valueOf(sum)
                        .divide(BigDecimal.valueOf(count), 1, RoundingMode.HALF_UP);
                averageUvPerHour.put(i, average.doubleValue());
            } else {
                averageUvPerHour.put(i, null);
            }
        }

        return Optional.of(averageUvPerHour);
    }

    @Override
    public Optional<List<Wind>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        return this.windDao.findByTimePeriod(startTime, endTime, regionId);
    }

    @Override
    public Optional<Wind> getLastFromCity(Integer cityId) {
        Wind lastFall = this.windDao.getLast(cityId).orElse(null);
        return Optional.ofNullable(lastFall);
    }
}
