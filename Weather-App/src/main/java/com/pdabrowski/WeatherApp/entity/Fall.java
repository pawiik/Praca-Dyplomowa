package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "fall")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "measurement_id")
public class Fall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurement_id;

    @Column(name="time")
    private Instant time;

    @Column(name="fall")
    private double fall;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "measurement_station_Id")
    private MeasurementStation measurementStation;

    public Fall(){}

    public Fall(Instant time, double fall) {
        this.time = time;
        this.fall = fall;
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

    public double getFall() {
        return fall;
    }

    public void setFall(double temperature) {
        this.fall = temperature;
    }

    public MeasurementStation getMeasurementStationId() {
        return measurementStation;
    }

    public void setMeasurementStationId(MeasurementStation measurementStationId) {
        this.measurementStation = measurementStationId;
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

    @Override
    public String toString() {
        return "Fall{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + fall +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
