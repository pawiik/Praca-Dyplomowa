package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "humidity")
public class Humidity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurement_id;

    @Column(name="time")
    private int time;

    @Column(name="temperature")
    private double temperature;

    @Column(name="measurement_station_id")
    private int measurementStationId;

    public Humidity(){}

    public Humidity(int time, double temperature, int measurementStationId) {
        this.time = time;
        this.temperature = temperature;
        this.measurementStationId = measurementStationId;
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

    public int getMeasurementStationId() {
        return measurementStationId;
    }

    public void setMeasurementStationId(int measurementStationId) {
        this.measurementStationId = measurementStationId;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + temperature +
                ", measurementStationId=" + measurementStationId +
                '}';
    }
}
