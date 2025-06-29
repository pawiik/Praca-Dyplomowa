package com.pdabrowski.WeatherApp;

import com.pdabrowski.WeatherApp.dao.*;
import com.pdabrowski.WeatherApp.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AlertDAOTests {

    @Autowired
    private AlertDAO alertDAO;

    @Autowired
    private RegionDAO regionDAOImplementation;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountDAO accountRepository;

    @Autowired
    private MeasurementStationDAO measurementStationDAO;

    @Autowired
    private FallDAO fallDAO;

    @Autowired
    private HumidityDAO humidityDAO;

    @Autowired
    private TemperatureDAO temperatureDAO;

    @Autowired
    private UVDAO uvdao;

    @Autowired
    private WindDAO windDAO;

    Region region;
    Alert foundAlert;

    @Before
    public void setup(){
//        this.region = new Region();
//        this.region.setName("sample name");
//        this.region = regionDAOImplementation.save(region);
    }

    @Test
    public void testRegionSaveAndFindById(){
        Region region1 = new Region();
        region1.setName("alaska");
        this.regionDAOImplementation.save(region1);

        Optional<Region> foundRegion = regionDAOImplementation.findById(region1.getRegionId());
        assertTrue(foundRegion.isPresent());
        assertEquals("alaska", foundRegion.get().getName());

        this.regionDAOImplementation.delete(region1);
    }

    @Test
    public void testAlertSaveAndFindById() {
        Region region = new Region();
        region.setName("ssample name");
        region = regionDAOImplementation.save(region);

        Alert newAlert = new Alert();
        newAlert.setMessage("Test Message");
        newAlert.setAlertType(1);
        newAlert.setStartTime(Instant.now());
        newAlert.setEndTime(Instant.now());
        region.addAlert(newAlert);

        Alert savedAlert = alertDAO.save(newAlert);

        Optional<Alert> foundAlert2 = alertDAO.findById(savedAlert.getAlertId());
        assertTrue(foundAlert2.isPresent());
        foundAlert = foundAlert2.get();
        assertEquals("Test Message", foundAlert2.get().getMessage());

        alertDAO.delete(foundAlert);
        regionDAOImplementation.delete(region);

    }

    @Test
    public void testCityAlertSaveAndFindById(){
        Region region = new Region();
        region.setName("sample name");
        region = regionDAOImplementation.save(region);

        City city = new City();
        city.setCityName("test name");
        region.addCity(city);

        City saved = cityDAO.save(city);
        Optional<City> foundCity = cityDAO.findById(saved.getCityId());
        assertTrue(foundCity.isPresent());
        saved = foundCity.get();
        assertEquals("test name", saved.getCityName());

        cityDAO.delete(saved);
        regionDAOImplementation.delete(region);
    }

//    @Test
//    public void testUserSaveAndFindById(){
//        Region region = new Region();
//        region.setName("sample name");
//        region = regionDAOImplementation.save(region);
//
//        City city = new City();
//        city.setCityName("test name");
//        region.addCity(city);
//        city = cityDAO.save(city);
//
//        UserDetails newUserDetails = org.springframework.security.core.userdetails.User.builder()
//                .username("test")
//                .password("test")
//                .roles("EMPLOYEE")
//                .build();
//
//        userDetailsManager.createUser(newUserDetails);
//
//        Account savedAccount = accountRepository.findByEmail("test").orElse(null);
//
//        User user = new User();
//        user.setUserId(savedAccount.getAccountId());
//        user.setEmail("test");
//        user.setAddress("test");
//        user.setPhoneNumber(123123123);
//        user.setName("test");
//        user.setLastName("test");
//        city.addUser(user);
//        region.addUser(user);
//
//        String id = userDAO.save(user).getUserId();
//
//        Optional<User> databaseUser = userDAO.findById(id);
//        assertTrue(databaseUser.isPresent());
//        assertEquals(databaseUser.get().getName(), "test");
//    }
//
//
//
    @Test
    public void testMeasurementStationSaveAndFindById(){
        Region region = new Region();
        region.setName("sample name");
        region = regionDAOImplementation.save(region);

        City city = new City();
        city.setCityName("test name");
        region.addCity(city);

        City saved = cityDAO.save(city);

        MeasurementStation measurementStation = new MeasurementStation();
        measurementStation.setAddress("test");
        measurementStation.setRegionId(region.getRegionId());
        city.addMeasurementStation(measurementStation);

        MeasurementStation databaseStation = measurementStationDAO.save(measurementStation);

        Optional<MeasurementStation> found = measurementStationDAO.findById(databaseStation.getStationId());
        assertTrue(found.isPresent());
        databaseStation = found.get();
        assertEquals(databaseStation.getAddress(), "test");

        measurementStationDAO.delete(databaseStation);
        cityDAO.delete(city);
        regionDAOImplementation.delete(region);
    }

    @Test
    public void testFallSaveAndFindById(){
        Region region = new Region();
        region.setName("sample name");
        region = regionDAOImplementation.save(region);

        City city = new City();
        city.setCityName("test name");
        region.addCity(city);

        City saved = cityDAO.save(city);

        MeasurementStation measurementStation = new MeasurementStation();
        measurementStation.setAddress("test");
        measurementStation.setRegionId(region.getRegionId());
        saved.addMeasurementStation(measurementStation);

        MeasurementStation databaseStation = measurementStationDAO.save(measurementStation);

        Fall fall = new Fall();
        fall.setFall(2d);
        fall.setTime(Instant.now());
        databaseStation.addFall(fall);

        Fall savedFall = fallDAO.save(fall);

        Optional<Fall> databaseFall = fallDAO.findById(savedFall.getMeasurement_id());
        assertTrue(databaseFall.isPresent());
        assertEquals(databaseFall.get().getFall(), 2d);


        fallDAO.delete(savedFall);
        measurementStationDAO.delete(databaseStation);
        cityDAO.delete(saved);
        regionDAOImplementation.delete(region);
    }


}