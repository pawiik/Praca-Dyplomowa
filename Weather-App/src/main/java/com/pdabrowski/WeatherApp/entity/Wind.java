package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "wind")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "measurementId")
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurementId;

    @Column(name="time")
    private int time;

    @Column(name="wind")
    private double wind;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "measurement_station_id")
    private MeasurementStation measurementStation;

    public Wind(){}

    public Wind(int time, double temperature) {
        this.time = time;
        this.wind = temperature;
    }

    public int getMeasurement_id() {
        return measurementId;
    }

    public void setMeasurement_id(int measurement_id) {
        this.measurementId = measurement_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTemperature() {
        return wind;
    }

    public void setTemperature(double temperature) {
        this.wind = temperature;
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
                "measurement_id=" + measurementId +
                ", time=" + time +
                ", temperature=" + wind +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
