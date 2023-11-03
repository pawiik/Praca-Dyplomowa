package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="region")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "regionId")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private Integer regionId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "regionId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<City> cities;

    @OneToMany(mappedBy = "region", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Alert> alerts;

    @ManyToMany(mappedBy = "regions")
    private Set<User> users = new HashSet<>();

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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int region_id) {
        this.regionId = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getRegionUsers() {
        return users;
    }

    public void setRegionUsers(Set<User> regionUsers) {
        this.users = regionUsers;
    }
}
