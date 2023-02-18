package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long>{
	List<Country> findByName(String name);
	List<Country> findByNameContaining(String name);
}