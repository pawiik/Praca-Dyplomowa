package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "region_id")
//    @JsonIgnore
    private Region region;

//    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = {CascadeType.ALL},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<MeasurementStation> measurementStations;

//    @JsonIgnore
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


    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", regionId=" + region +
                '}';
    }
}
