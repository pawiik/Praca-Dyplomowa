package com.pdabrowski.WeatherApp;

import com.pdabrowski.WeatherApp.dao.AlertDAO;
import com.pdabrowski.WeatherApp.dao.CityDAO;
import com.pdabrowski.WeatherApp.dao.RegionDAO;
import com.pdabrowski.WeatherApp.entity.Alert;
import com.pdabrowski.WeatherApp.entity.City;
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
	public CommandLineRunner commandLineRunner(RegionDAO studentDAO, CityDAO cityDAO, AlertDAO alertDAO) {
		return runner -> {
			System.out.println("Hello world");
//			addRegion(studentDAO);

//			addCity(cityDAO, studentDAO);

			addAlert(studentDAO, alertDAO);

		};
	}

	private void addAlert(RegionDAO regionDAO, AlertDAO alertDAO) {
		Region existingRegion = regionDAO.findById(17).orElse(null);

		if (existingRegion != null) {
			Alert newAlert = new Alert(1, 2, "bad weather", 1);
			existingRegion.addAlert(newAlert);
			alertDAO.save(newAlert);
		}
	}

	private void addCity(CityDAO cityDAO, RegionDAO regionDAO) {
		Region existingRegion = regionDAO.findById(17).orElse(null);

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
