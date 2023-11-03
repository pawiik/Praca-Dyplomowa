package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

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
    private int time;

    @Column(name="fall")
    private double temperature;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "measurement_station_id")
    private MeasurementStation measurementStation;

    public Fall(){}

    public Fall(int time, double temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurement_id = measurement_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public MeasurementStation getMeasurementStationId() {
        return measurementStation;
    }

    public void setMeasurementStationId(MeasurementStation measurementStationId) {
        this.measurementStation = measurementStationId;
    }

    @Override
    public String toString() {
        return "Fall{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + temperature +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
