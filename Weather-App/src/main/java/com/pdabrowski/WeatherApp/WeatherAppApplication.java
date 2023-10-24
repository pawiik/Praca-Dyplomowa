package com.pdabrowski.WeatherApp;

import com.pdabrowski.WeatherApp.dao.RegionDAO;
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
	public CommandLineRunner commandLineRunner(RegionDAO studentDAO) {
		return runner -> {
			System.out.println("Hello world");
			addRegion(studentDAO);
		};
	}

	public Region addRegion(RegionDAO regionDAO) {
		Region region = new Region("Wroclaw");
		regionDAO.save(region);
		return null;
	}

}
