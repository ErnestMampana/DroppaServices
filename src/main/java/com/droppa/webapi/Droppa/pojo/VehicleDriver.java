package com.droppa.webapi.Droppa.pojo;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class VehicleDriver {

	@NotNull
	private String id;
	private String name;
	private String surname;
	private long celphone;
	private byte[] driverLicence;
	private List<Vehicle> vehicles;

	public VehicleDriver() {
		super();
	}

	public VehicleDriver(@NotNull String id, String name, String surname, long celphone, byte[] driverLicence,
			List<Vehicle> vehicles) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.celphone = celphone;
		this.driverLicence = driverLicence;
		this.vehicles = vehicles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getCelphone() {
		return celphone;
	}

	public void setCelphone(long celphone) {
		this.celphone = celphone;
	}

	public byte[] getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(byte[] driverLicence) {
		this.driverLicence = driverLicence;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
