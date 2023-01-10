package com.droppa.webapi.DroppaServices.controllers;

import java.util.List;
import com.droppa.webapi.DroppaServices.CountryService;
import com.droppa.webapi.DroppaServices.bean.Country;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("countries")
public class CountryController {
	CountryService countryService = new CountryService();

	@GET
	@Path("/viewall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountries() {

		List<Country> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}
	
	@GET
	@Path("/string")
	@Produces(MediaType.APPLICATION_JSON)
	public String testService() {
		return "Test done";
		
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country getCountryById(@PathParam("id") int id) {
		return countryService.getCountry(id);
	}

	@POST
	@Path("/addcountry")
	@Produces(MediaType.APPLICATION_JSON)
	public Country addCountry(Country country) {
		return countryService.addCountry(country);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Country updateCountry(Country country) {
		return countryService.updateCountry(country);

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCountry(@PathParam("id") int id) {
		countryService.deleteCountry(id);

	}
}
