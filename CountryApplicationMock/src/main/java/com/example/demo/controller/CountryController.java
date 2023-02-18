package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Country;
import com.example.demo.model.CountryRepository;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	CountryRepository countryRepo;
	
	@GetMapping("/countries")
	public ResponseEntity<List<Country>>getAllCountry(@RequestParam(required=false) String name){
		try {
			List<Country> countries = name == null ? countryRepo.findAll():countryRepo.findByNameContaining(name);
			
			if(countries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(countries, HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/countries/{id}")
	public ResponseEntity<Country> getCountry(@PathVariable Long id){
		try {
			Optional<Country> country = countryRepo.findById(id);
			if (country.isPresent()){
				return new ResponseEntity<>(country.get(), HttpStatus.OK);		
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/countries")
	public ResponseEntity<Country> createCountry(@RequestBody Country country){
		try {
			Country newCountry = new Country(country.getName(), country.getPopulation(), country.getCapital());
			newCountry = countryRepo.save(newCountry); 	// no need to have 'newCountry ='
			return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
		} catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/countries/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country updatedCountry){
		try {
			Optional<Country> existingCountry = countryRepo.findById(id);
			if (existingCountry.isPresent()){
				Country country = existingCountry.get();
				country.setName(updatedCountry.getName());
				country.setPopulation(updatedCountry.getPopulation());
				countryRepo.save(country);
				return new ResponseEntity<>(country, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllCountries(){
		try {
			countryRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/countries/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
		try {
			Optional<Country> existingCountry = countryRepo.findById(id);
			if (existingCountry.isPresent()) {
				countryRepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
