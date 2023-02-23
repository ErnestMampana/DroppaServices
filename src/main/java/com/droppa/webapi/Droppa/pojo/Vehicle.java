package com.droppa.webapi.Droppa.pojo;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class Vehicle {

	@NotNull
	private String registration;
	private String make;
	private String type;
	private String discExpiryDate;
	private List<VehicleDriver> drivers;
	private Company company;
	
	
	public Vehicle() {
		super();
	}


	public Vehicle(@NotNull String registration, String make, String type, String discExpiryDate,
			List<VehicleDriver> drivers, Company company) {
		super();
		this.registration = registration;
		this.make = make;
		this.type = type;
		this.discExpiryDate = discExpiryDate;
		this.drivers = drivers;
		this.company = company;
	}


	public String getRegistration() {
		return registration;
	}


	public void setRegistration(String registration) {
		this.registration = registration;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDiscExpiryDate() {
		return discExpiryDate;
	}


	public void setDiscExpiryDate(String discExpiryDate) {
		this.discExpiryDate = discExpiryDate;
	}


	public List<VehicleDriver> getDrivers() {
		return drivers;
	}


	public void setDrivers(List<VehicleDriver> drivers) {
		this.drivers = drivers;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}
	
}
