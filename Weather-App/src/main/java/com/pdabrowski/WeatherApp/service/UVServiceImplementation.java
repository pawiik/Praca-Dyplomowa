package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.UVDAO;
import com.pdabrowski.WeatherApp.entity.Humidity;
import com.pdabrowski.WeatherApp.entity.UV;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class UVServiceImplementation implements UVService{

    UVDAO uvDao;

    @Autowired
    public UVServiceImplementation(UVDAO uvdao){
        this.uvDao = uvdao;
    }
    @Override
    @Transactional
    public UV saveUV(UV uv) {
        return uvDao.save(uv);
    }

    @Override
    @Transactional
    public Optional<UV> getUVById(int id) {
        return uvDao.findById(id);
    }

    @Override
    @Transactional
    public List<UV> getAllUVs() {
        return uvDao.findAll();
    }

    @Override
    @Transactional
    public void deleteUV(UV uv) {
        uvDao.delete(uv);
    }

    @Override
    public Optional<Map<Integer, Double>> getByDay(Instant day, Integer cityId) throws ParseException {

        List<UV> measurements = this.uvDao.getAllFromCityDay(cityId, day).orElse(null);

        Map<Integer, Double> sumOfMeasurementsPerHour = new HashMap<>();
        Map<Integer, Integer> countOfMeasurementsPerHour = new HashMap<>();

        assert measurements != null;
        for (UV measurement : measurements) {
            Calendar calendar = Calendar.getInstance();
            Date dateFromInstant = Date.from(measurement.getTime());
            calendar.setTime(dateFromInstant);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            sumOfMeasurementsPerHour.merge(hour, measurement.getTemperature(), Double::sum);
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
    public Optional<List<UV>> getByTimePeriod(Instant startTime, Instant endTime, Integer regionId) {
        return this.uvDao.findByTimePeriod(startTime, endTime, regionId);
    }

    @Override
    public Optional<UV> getLastFromCity(Integer cityId) {
        UV lastFall = this.uvDao.getLast(cityId).orElse(null);
        return Optional.ofNullable(lastFall);
    }
}
