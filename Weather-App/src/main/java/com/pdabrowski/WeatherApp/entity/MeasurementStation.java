package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "measurement_station")
public class MeasurementStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private int stationId;

//    @Column(name = "city_id")
//    private int cityId;

    @Column(name = "address")
    private String address;

    @Column(name = "region_id")
    private int regionId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    private City city;

    public MeasurementStation(){
    }

    public MeasurementStation(String address, int regionId) {
        this.address = address;
        this.regionId = regionId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setCity(City stationId) {
        this.city = stationId;
        System.out.println("dupa");
    }

    public City getCity() {
        return city;
    }

//    public void setCityId(int cityId) {
//        this.cityId = cityId;
//    }

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
        return "MeasurementStation{" +
                "stationId=" + stationId +
                ", cityId=" + city +
                ", address='" + address + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
