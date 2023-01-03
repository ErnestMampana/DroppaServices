package com.droppa.webapi.DroppaServices.bean;

public class Country {
	int id;
	String name;
	long population;
	
	
	public Country() {
		super();
	}

	public Country(int id, String name, long population) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPopulation() {
		return population;
	}
	
	public void setPopulation(long population) {
		this.population = population;
	}
	
	
}
