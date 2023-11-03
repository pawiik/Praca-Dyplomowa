package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "cityId")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "name")
    private String cityName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "region_id")
    private Region regionId;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<MeasurementStation> measurementStations;

    @OneToMany(mappedBy = "city")
    private List<User> users;

    public City(){}

    public City(String cityName) {
        this.cityName = cityName;
    }

    public void addMeasurementStation(MeasurementStation theStation){
        if(measurementStations == null){
            measurementStations = new ArrayList<>();
        }

        measurementStations.add(theStation);
        theStation.setCity(this);
    }
    public void addUser(User theUser){
        if(users == null){
            users = new ArrayList<>();
        }

        users.add(theUser);
        theUser.setCity(this);
    }


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Region getRegion() {
        return regionId;
    }

    public void setRegion(Region regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
