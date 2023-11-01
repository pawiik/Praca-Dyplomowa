package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Fall> fall;

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Humidity> humidity;

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Temperature> temperature;

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<UV> uv;

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Wind> wind;

    @OneToMany(mappedBy = "measurementStation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Employee> employees;

    public MeasurementStation(){
    }

    public MeasurementStation(String address, int regionId) {
        this.address = address;
        this.regionId = regionId;
    }

    public void addEmployee(Employee newEmployee){
        if(employees == null){
            employees = new ArrayList<>();
        }
        employees.add(newEmployee);
        newEmployee.setMeasurementStation(this);
    }

    public void addFall(Fall newFall){
        if(fall == null){
            fall = new ArrayList<>();
        }

        fall.add(newFall);
        newFall.setMeasurementStationId(this);
    }

    public void addTemperature(Temperature newTemperature){
        if(temperature == null){
            temperature = new ArrayList<>();
        }

        temperature.add(newTemperature);
        newTemperature.setMeasurementStation(this);
    }

    public void addHumidity(Humidity newHumidity){
        if(humidity == null){
            humidity = new ArrayList<>();
        }

        humidity.add(newHumidity);
        newHumidity.setMeasurementStation(this);
    }

    public void addUV(UV newUV){
        if(uv == null){
            uv = new ArrayList<>();
        }

        uv.add(newUV);
        newUV.setMeasurementStation(this);
    }

    public void addWind(Wind newWind){
        if(wind == null){
            wind = new ArrayList<>();
        }

        wind.add(newWind);
        newWind.setMeasurementStation(this);
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
