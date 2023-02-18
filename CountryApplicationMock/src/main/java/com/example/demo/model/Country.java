package com.example.demo.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="population")
	private int population;
	
	@Column(name="capital")
	private ArrayList<String> capital;
	
	public Country() {}

	public Country(String name, int population, ArrayList<String> capital) {
		this.name = name;
		this.population = population;
		this.capital = capital;
	}


	public ArrayList<String> getCapital() {
		return capital;
	}

	public void setCapital(ArrayList<String> capital) {
		this.capital = capital;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
