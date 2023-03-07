package com.droppa.webapi.DroppaServices.pojo;

import com.droppa.webapi.DroppaServices.core.AccountStatus;

import jakarta.validation.constraints.NotNull;

public class DriverAccount {
	
	@NotNull
	private String id;
	private Vehicle vehicle;
	private VehicleDriver driver;
	private AccountStatus status;
	
	
	public DriverAccount() {
		super();
	}


	public DriverAccount(@NotNull String id, Vehicle vehicle, VehicleDriver driver, AccountStatus status) {
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


	public AccountStatus getStatus() {
		return status;
	}


	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
}
