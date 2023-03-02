package com.droppa.webapi.DroppaServices.restApi;

import java.util.List;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.DTO.DriverDTO;
import com.droppa.webapi.DroppaServices.bean.DriverService;
import com.droppa.webapi.DroppaServices.pojo.DriverAccount;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.VehicleDriver;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverRestApi {
	
	@EJB
	DriverService driverService = new DriverService();
	
	@GET
	@Path("/viewalldrivers")
	public Response getAllUsers() {
		List<DriverAccount> drivers = driverService.getAllDrivers();
		return Response.ok().entity(drivers).build();
	}
	
	
	@POST
	@Path("/createdriver")
	public Response createUser(DriverDTO driver) {
		driverService.createDriverAccount(driver);
		return Response.ok().entity(driver).build();
	}
	
	@GET
	@Path("/getdriverbyid/{driverId}")
	public Response getDriverById(@PathParam("driverId") String driverId) {
		DriverAccount driverAcc = driverService.getDriverById(driverId);
		return Response.ok().entity(driverAcc).build();
	}
}
