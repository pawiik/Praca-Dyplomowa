package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.HumidityDAO;
import com.pdabrowski.WeatherApp.entity.Humidity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class HumidityServiceImplementation implements HumidityService{

    HumidityDAO humidityDao;

    @Autowired
    public HumidityServiceImplementation(HumidityDAO humidityDAO){
        this.humidityDao = humidityDAO;
    }

    @Override
    @Transactional
    public Humidity saveHumidity(Humidity humidity) {
        return humidityDao.save(humidity);
    }

    @Override
    @Transactional
    public Optional<Humidity> getHumidityById(int id) {
        return humidityDao.findById(id);
    }

    @Override
    @Transactional
    public List<Humidity> getAllHumidities() {
        return humidityDao.findAll();
    }

    @Override
    @Transactional
    public void deleteHumidity(Humidity humidity) {
        humidityDao.delete(humidity);
    }

    @Override
    public Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException {

        List<Humidity> measurements = this.humidityDao.getAllFromCityDay(cityId, day).orElse(null);

        Map<Integer, Double> sumOfMeasurementsPerHour = new HashMap<>();
        Map<Integer, Integer> countOfMeasurementsPerHour = new HashMap<>();

        assert measurements != null;
        for (Humidity measurement : measurements) {
            Calendar calendar = Calendar.getInstance();
            Date dateFromInstant = Date.from(measurement.getTime());
            calendar.setTime(dateFromInstant);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            sumOfMeasurementsPerHour.merge(hour, measurement.getHumidity(), Double::sum);
            countOfMeasurementsPerHour.merge(hour, 1, Integer::sum);
        }

        Map<Integer, Double> averageHumidityPerHour = new HashMap<>();

        for (int i = 0; i < 24; i++) {
            if (countOfMeasurementsPerHour.containsKey(i) && sumOfMeasurementsPerHour.containsKey(i)) {
                double sum = sumOfMeasurementsPerHour.get(i);
                double count = countOfMeasurementsPerHour.get(i);
                BigDecimal average = BigDecimal.valueOf(sum)
                        .divide(BigDecimal.valueOf(count), 1, RoundingMode.HALF_UP);
                averageHumidityPerHour.put(i, average.doubleValue());
            } else {
                averageHumidityPerHour.put(i, null);
            }
        }

        return Optional.of(averageHumidityPerHour);
    }

    @Override
    public Optional<List<Humidity>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        return this.humidityDao.findByTimePeriod(startTime, endTime, regionId);
    }

    @Override
    public Optional<Humidity> getLastFromCity(Integer cityId) {
        Humidity lastFall = this.humidityDao.getLast(cityId).orElse(null);
        return Optional.ofNullable(lastFall);
    }
}
