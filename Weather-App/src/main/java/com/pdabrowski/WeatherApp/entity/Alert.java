package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "alert")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "alertId")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private int alertId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "message")
    private String message;

    @Column(name = "alert_type")
    private int alertType;

    public Alert(){}

    public Alert(Instant startTime, Instant endTime, String message, int alertType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
        this.alertType = alertType;
    }

    @JsonProperty("region")
    public Map<String, Object> getRegionDetails() {
        Map<String, Object> regionDetails = new HashMap<>();
        if (region != null) {
            regionDetails.put("regionId", region.getRegionId());
            regionDetails.put("name", region.getName());
        }
        return regionDetails;
    }

    @JsonIgnore
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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



    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
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
