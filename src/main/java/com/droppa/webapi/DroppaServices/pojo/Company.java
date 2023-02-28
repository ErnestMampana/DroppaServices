package com.droppa.webapi.DroppaServices.pojo;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Company {

	@NotNull
	@NotBlank
	@NotEmpty
	private String id;
	private String companyName;
	private Person owner;
	private List<Vehicle> listOfVehicles;
	private List<VehicleDriver> drivers;
	private String location;

	public Company() {
		super();
	}

	public Company(@NotNull @NotBlank @NotEmpty String id, String companyName, Person owner,
			List<Vehicle> listOfVehicles, List<VehicleDriver> drivers, String location) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.owner = owner;
		this.listOfVehicles = listOfVehicles;
		this.drivers = drivers;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public List<Vehicle> getListOfVehicles() {
		return listOfVehicles;
	}

	public void setListOfVehicles(List<Vehicle> listOfVehicles) {
		this.listOfVehicles = listOfVehicles;
	}

	public List<VehicleDriver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<VehicleDriver> drivers) {
		this.drivers = drivers;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
