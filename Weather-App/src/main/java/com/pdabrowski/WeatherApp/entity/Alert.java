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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "message")
    private String message;

    @Column(name = "alert_type")
    private int alertType;

    public Alert(){}

    public Alert(int startTime, int endTime, String message, int alertType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
        this.alertType = alertType;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId=" + alertId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", regionId=" + region +
                ", message='" + message + '\'' +
                ", aletrType=" + alertType +
                '}';
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Region getRegion() {
        return region;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }
}
