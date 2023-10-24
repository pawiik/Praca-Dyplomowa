package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="temperature")
public class Temperature {

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

    public Temperature(){}

    public Temperature(int time, double temperature, int measurementStationId) {
        this.time = time;
        this.temperature = temperature;
        this.measurementStationId = measurementStationId;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + temperature +
                ", measurementStationId=" + measurementStationId +
                '}';
    }
}
