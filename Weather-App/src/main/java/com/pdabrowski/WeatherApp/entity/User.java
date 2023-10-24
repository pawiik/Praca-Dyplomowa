package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "address")
    private String address;

    @Column(name = "region_id")
    private int regionId;

    public User(){}

    public User(int cityId, String address, int regionId) {
        this.cityId = cityId;
        this.address = address;
        this.regionId = regionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", cityId=" + cityId +
                ", address='" + address + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
