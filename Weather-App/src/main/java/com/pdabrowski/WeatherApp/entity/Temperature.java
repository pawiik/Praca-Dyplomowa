package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="temperature")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "measurement_id")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurement_id;

    @Column(name="time")
    private Instant time;

    @Column(name="temperature")
    private double temperature;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "measurement_station_id")
    private MeasurementStation measurementStation;

    public Temperature(){}

    public Temperature(Instant time, double temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurement_id = measurement_id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("measurementStationId")
    public Map<String, Object> getRegionDetails() {
        Map<String, Object> station = new HashMap<>();
        if (measurementStation != null) {
            station.put("stationId", measurementStation.getStationId());
            station.put("address", measurementStation.getAddress());
            station.put("city", measurementStation.getRegionDetails());
        }
        return station;
    }

    public void setMeasurementStation(MeasurementStation measurementStation) {
        this.measurementStation = measurementStation;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + temperature +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
