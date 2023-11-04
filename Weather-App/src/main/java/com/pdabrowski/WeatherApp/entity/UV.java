package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "uv")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "measurement_id"
)
public class UV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "measurement_id")
    private int measurement_id;

    @Column(name="time")
    private int time;

    @Column(name="uv")
    private double uv;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "measurement_station_id")
    private MeasurementStation measurementStation;

    public UV(){}

    public UV(int time, double uv) {
        this.time = time;
        this.uv = uv;
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
        return uv;
    }

    public void setTemperature(double temperature) {
        this.uv = temperature;
    }

    public MeasurementStation getMeasurementStation() {
        return measurementStation;
    }

    public void setMeasurementStation(MeasurementStation measurementStationId) {
        this.measurementStation = measurementStationId;
    }

    @Override
    public String toString() {
        return "UV{" +
                "measurement_id=" + measurement_id +
                ", time=" + time +
                ", temperature=" + uv +
                ", measurementStationId=" + measurementStation +
                '}';
    }
}
