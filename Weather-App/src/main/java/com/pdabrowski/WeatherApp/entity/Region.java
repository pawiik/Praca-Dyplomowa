package com.pdabrowski.WeatherApp.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private int region_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "regionId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<City> cities;

    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Alert> alerts;



    public Region(){

    }

    public void addCity(City newCity){

        if(cities == null){
            cities = new ArrayList<>();
        }

        cities.add(newCity);
        newCity.setRegion(this);
    }

    public void addAlert(Alert newAlert){

        if(alerts == null){
            alerts = new ArrayList<>();
        }

        alerts.add(newAlert);
        newAlert.setRegion(this);

    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Region(String name) {
        this.name = name;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
