package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.RegionDAO;
import com.pdabrowski.WeatherApp.dao.UserDAO;
import com.pdabrowski.WeatherApp.entity.Region;
import com.pdabrowski.WeatherApp.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImplementation implements RegionService{

    @Autowired
    private RegionDAO regionRepository;

    @Autowired
    private UserDAO userRepository;

    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public void addUserToRegion(User user, Region region) {

    }

    @Override
    public Region getRegionById(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Region> getAllRegions() {
        List<Region> regions = regionRepository.findAll();

        return regions;
    }
}
