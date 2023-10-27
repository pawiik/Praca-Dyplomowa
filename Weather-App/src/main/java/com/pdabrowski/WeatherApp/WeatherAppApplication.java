package com.pdabrowski.WeatherApp;

import com.pdabrowski.WeatherApp.dao.AlertDAO;
import com.pdabrowski.WeatherApp.dao.CityDAO;
import com.pdabrowski.WeatherApp.dao.MeasurementStationDAO;
import com.pdabrowski.WeatherApp.dao.RegionDAO;
import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.City;
import com.pdabrowski.WeatherApp.entity.MeasurementStation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.pdabrowski.WeatherApp.entity.Region;

@SpringBootApplication
public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			RegionDAO regionDAO,
			CityDAO cityDAO,
			AlertDAO alertDAO,
			MeasurementStationDAO measurementStationDAO) {
		
		return runner -> {
			System.out.println("Hello world");
//			addRegion(regionDAO);

//			addCity(cityDAO, regionDAO);

//			addAlert(regionDAO, alertDAO);
			
//			addMeasurementStation(cityDAO, measurementStationDAO);

		};
	}

	private void addMeasurementStation(CityDAO cityDAO, MeasurementStationDAO measurementStationDAO) {
		City existingCity = cityDAO.findById(6).orElse(null);
		System.out.println("got city");
		if (existingCity != null) {
			MeasurementStation newMeasurementStation = new MeasurementStation("adress", 21);
			existingCity.addMeasurementStation(newMeasurementStation);
			measurementStationDAO.save(newMeasurementStation);
		}
	}

	private void addAlert(RegionDAO regionDAO, AlertDAO alertDAO) {
		Region existingRegion = regionDAO.findById(21).orElse(null);

		if (existingRegion != null) {
			Alert newAlert = new Alert(1, 2, "bad weather", 1);
			existingRegion.addAlert(newAlert);
			alertDAO.save(newAlert);
		}
	}

	private void addCity(CityDAO cityDAO, RegionDAO regionDAO) {
		Region existingRegion = regionDAO.findById(21).orElse(null);

		if (existingRegion != null) {
			City newCity = new City();
			newCity.setCityName("New City Name");
			existingRegion.addCity(newCity);
			cityDAO.save(newCity);
		}

	}

	public Region addRegion(RegionDAO regionDAO) {
		Region region = new Region("Wroclaw");
		regionDAO.save(region);
		return null;
	}

}
