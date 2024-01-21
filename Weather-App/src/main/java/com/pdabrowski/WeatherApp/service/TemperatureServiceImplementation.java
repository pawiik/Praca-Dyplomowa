package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.TemperatureDAO;
import com.pdabrowski.WeatherApp.entity.Fall;
import com.pdabrowski.WeatherApp.entity.Temperature;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class TemperatureServiceImplementation implements TemperatureService {

    TemperatureDAO temperatureDao;

    @Autowired
    public TemperatureServiceImplementation(TemperatureDAO temperatureDAO){
        this.temperatureDao = temperatureDAO;
    }
    @Override
    @Transactional
    public Temperature saveTemperature(Temperature temperature) {
        return temperatureDao.save(temperature);
    }

    @Override
    @Transactional
    public Optional<Temperature> getTemperatureById(int id) {
        return temperatureDao.findById(id);
    }

    @Override
    @Transactional
    public List<Temperature> getAllTemperatures() {
        return temperatureDao.findAll();
    }

    @Override
    @Transactional
    public void deleteTemperature(Temperature temperature) {
        temperatureDao.delete(temperature);
    }

    @Override
    public Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException {

        List<Temperature> temperatures = this.temperatureDao.getAllFromCityDay(cityId, day).orElse(null);

        Map<Integer, Double> sumOfTemperaturesPerHour = new HashMap<>();
        Map<Integer, Integer> countOfTemperaturesPerHour = new HashMap<>();

        assert temperatures != null;
        for (Temperature temperature : temperatures) {
            Calendar calendar = Calendar.getInstance();
            Date dateFromInstant = Date.from(temperature.getTime());
            calendar.setTime(dateFromInstant);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            sumOfTemperaturesPerHour.merge(hour, temperature.getTemperature(), Double::sum);
            countOfTemperaturesPerHour.merge(hour, 1, Integer::sum);
        }

        Map<Integer, Double> averageTemperaturePerHour = new HashMap<>();

        for (int i = 0; i < 24; i++) {
            if (countOfTemperaturesPerHour.containsKey(i) && sumOfTemperaturesPerHour.containsKey(i)) {
                double sum = sumOfTemperaturesPerHour.get(i);
                double count = countOfTemperaturesPerHour.get(i);
                BigDecimal average = BigDecimal.valueOf(sum)
                        .divide(BigDecimal.valueOf(count), 1, RoundingMode.HALF_UP);
                averageTemperaturePerHour.put(i, average.doubleValue());
            } else {
                averageTemperaturePerHour.put(i, null);
            }
        }

        return Optional.of(averageTemperaturePerHour);
    }

    @Override
    public Optional<List<Temperature>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        return this.temperatureDao.findByTimePeriod(startTime, endTime, regionId);
    }

    @Override
    public Optional<Temperature> getLastFromCity(Integer cityId) {
        Temperature lastFall = this.temperatureDao.getLast(cityId).orElse(null);
        return Optional.ofNullable(lastFall);
    }
}
