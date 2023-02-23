package com.droppa.webapi.Droppa.pojo;

import jakarta.validation.constraints.NotNull;

public class DriverAccount {
	
	@NotNull
	private String id;
	private Vehicle vehicle;
	private VehicleDriver driver;
	private String status;
	
	
	public DriverAccount() {
		super();
	}


	public DriverAccount(@NotNull String id, Vehicle vehicle, VehicleDriver driver, String status) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.driver = driver;
		this.status = status;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public VehicleDriver getDriver() {
		return driver;
	}


	public void setDriver(VehicleDriver driver) {
		this.driver = driver;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
