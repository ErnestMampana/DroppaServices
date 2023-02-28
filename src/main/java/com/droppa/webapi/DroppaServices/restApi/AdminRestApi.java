package com.droppa.webapi.DroppaServices.restApi;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.Auth.Secured;
import com.droppa.webapi.DroppaServices.bean.AdminService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

//@Secured
@Path("admin")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AdminRestApi {
	
	@EJB
	private AdminService adminService = new AdminService();
	
	
	@POST
	@Path("/suspenddriver/{driverId}")
	public Response suspendDriver(@PathParam("driverId") String driverId) {
		String entity = adminService.suspendDriver(driverId);
		return Response.ok().entity(entity).build();
	}
	
	
	@POST
	@Path("/activatedriver/{driverId}")
	public Response activateDriver(@PathParam("driverId") String driverId) {
		String entity = adminService.confirmDriver(driverId);
		return Response.ok().entity(entity).build();
	}

}
