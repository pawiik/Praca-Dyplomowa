package com.pdabrowski.WeatherApp;

import com.pdabrowski.WeatherApp.dao.*;
import com.pdabrowski.WeatherApp.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			MeasurementStationDAO measurementStationDAO,
			FallDAO fallDAO,
			HumidityDAO humidityDAO,
			TemperatureDAO temperatureDAO,
			UVDAO uvDAO,
			WindDAO windDAO
			) {
		
		return runner -> {
			System.out.println("Hello world");
//			addRegion(regionDAO);

//			addCity(cityDAO, regionDAO);

//			addAlert(regionDAO, alertDAO);
			
//			addMeasurementStation(cityDAO, measurementStationDAO);

//			addMeasurements(measurementStationDAO ,fallDAO, humidityDAO, temperatureDAO, uvDAO, windDAO);



		};
	}

	private void addMeasurements(
			MeasurementStationDAO stationDAO,
			FallDAO fallDAO,
			HumidityDAO humidityDAO,
			TemperatureDAO temperatureDAO,
			UVDAO uvDAO,
			WindDAO windDAO) {

		MeasurementStation existingStation = stationDAO.findById(1).orElse(null);

		if (existingStation != null) {
			Fall newFall = new Fall(1, 2);
			Humidity newHumidity = new Humidity(1, 2);
			Temperature newTemperature = new Temperature(1, 2);
			UV newUV = new UV(1, 2);
			Wind newWind = new Wind(1, 2);

			existingStation.addFall(newFall);
			fallDAO.save(newFall);

			existingStation.addHumidity(newHumidity);
			humidityDAO.save(newHumidity);

			existingStation.addTemperature(newTemperature);
			temperatureDAO.save(newTemperature);

			existingStation.addUV(newUV);
			uvDAO.save(newUV);

			existingStation.addWind(newWind);
			windDAO.save(newWind);
		}

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
			newCity.setCityName("Name");
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
