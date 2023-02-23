package com.droppa.webapi.DroppaServices.restApi;

import java.util.List;
import javax.ejb.EJB;

import com.droppa.webapi.Droppa.DTO.VehicleDTO;
import com.droppa.webapi.Droppa.pojo.Vehicle;
import com.droppa.webapi.DroppaServices.bean.VehicleService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleRestApi {
	
	@EJB
	VehicleService vehicleService = new VehicleService();

	@POST
	@Path("/registervehicle")
	public Response registerVegicle(VehicleDTO vehicleDto) {
		Vehicle ve =  vehicleService.registerVehicle(vehicleDto);
		return Response.ok().entity(ve).build();
	}
	
	@GET
	@Path("/viewallvehicles")
	public Response viewAllVehicles() {
		List<Vehicle> users = vehicleService.getAllVehicles();
		return Response.ok().entity(users).build();
	}
	
	
	
}
