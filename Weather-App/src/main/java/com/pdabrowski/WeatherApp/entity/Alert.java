package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private int alertId;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

    @Column(name = "region_id")
    private int regionId;

    @Column(name = "message")
    private String message;

    @Column(name = "alert_type")
    private int aletrType;

    public Alert(){}

    public Alert(int startTime, int endTime, int regionId, String message, int aletrType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.regionId = regionId;
        this.message = message;
        this.aletrType = aletrType;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId=" + alertId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", regionId=" + regionId +
                ", message='" + message + '\'' +
                ", aletrType=" + aletrType +
                '}';
    }
}
