package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Country;
import com.example.demo.model.CountryRepository;

@SpringBootApplication
public class CountryApplicationMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryApplicationMockApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CountryRepository countryRepo) {
		return arg -> {
			countryRepo.save(new Country("United States", 331142779, new ArrayList<String>(Arrays.asList("LA", "Washingston"))));
			countryRepo.save(new Country("Spain", 47450795, new ArrayList<String>(Arrays.asList("Madrid", "Sevilla"))));
			countryRepo.save(new Country("Japan", 125570000, new ArrayList<String>(Arrays.asList("Tokyo", "Osaka", "Okinawa"))));
//			countryRepo.save(new Country("Hong Kong", 8012345));
		};
	}
}
