package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wind")
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurement_id;

    @Column(name="time")
    private int time;

    @Column(name="wind")
    private double temperature;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "measurement_station_id")
    private MeasurementStation measurementStation;

    public Wind(){}

    public Wind(int time, double temperature) {
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

    public MeasurementStation getMeasurementStation() {
        return measurementStation;
    }

    public void setMeasurementStation(MeasurementStation measurementStationId) {
        this.measurementStation = measurementStationId;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + temperature +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
