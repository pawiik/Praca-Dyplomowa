package com.pdabrowski.WeatherApp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    // This is the non-owning side of the relation
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "region_has_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    private List<Region> regions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public User(){}

    public User(String address) {
        this.address = address;
    }

//    public void addRegionToMain(Region region){
//        if (this.regions == null){
//            this.regions = new ArrayList<>();
//        }
//        this.regions.add(region);
//    }

//    public void removeRegionFromFavourite(Region region){
//        if (this.regions == null){
//            return;
//        }
//        this.regions.remove(region);
//    }
public void addRegion(Region region) {
    this.regions.add(region);
    region.getRegionUsers().add(this);
}

    // Corresponding removeRegion method
    public void removeRegion(Region region) {
        this.regions.remove(region);
        region.getRegionUsers().remove(this);
    }
    public int getUserId() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Region> getRegion() {
        return regions;
    }

    public void setRegionUsers(List<Region> regionUsers) {
        this.regions = regionUsers;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

//    public int getCityId() {
//        return cityId;
//    }

//    public void setCityId(int cityId) {
//        this.cityId = cityId;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public List<Region> getRegion() {
//        return regions;
//    }

//    public void setRegion(List<Region> regions) {
//        this.regions = regions;
//    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", cityId=" + cityId +
//                ", address='" + address + '\'' +
//                ", regionId=" + regions +
//                '}';
//    }
}
