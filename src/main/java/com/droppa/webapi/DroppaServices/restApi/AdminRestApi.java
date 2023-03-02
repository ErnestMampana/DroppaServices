package com.droppa.webapi.DroppaServices.restApi;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.Auth.Secured;
import com.droppa.webapi.DroppaServices.bean.AdminService;
import com.droppa.webapi.DroppaServices.pojo.Booking;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//@Secured
@Path("admin")
@Produces({"application/json"})
@Consumes({"application/json"})
//@RolesAllowed({"admin"})
public class AdminRestApi {
	
	@EJB
	private AdminService adminService = new AdminService();
	
	
	@PUT
	@Path("/suspenddriver/{driverId}")
	public Response suspendDriver(@PathParam("driverId") String driverId) {
		String entity = adminService.suspendDriver(driverId);
		return Response.ok().entity(entity).build();
	}
	
	
	@PUT
	@Path("/activatedriver/{driverId}")
	public Response activateDriver(@PathParam("driverId") String driverId) {
		String entity = adminService.confirmDriver(driverId);
		return Response.ok().entity(entity).build();
	}
	
	@PUT
	@Path("/assigndriver/{bookingId}/{driverId}")
	public Response asignBookingToDriver(@PathParam ("bookingId") String bookingId,@PathParam("driverId") String driverId) {
		Booking booking = adminService.asignBookingToDriver(bookingId, driverId);
		return Response.ok(booking, MediaType.APPLICATION_JSON).build();
	}
	
	
	@DELETE
	@Path("/deletebooking/{bookingId}")
	public Response deleteBooking(@PathParam("bookingId") String bbokingId) {
		String message = adminService.deleteBooking(bbokingId);
		return Response.ok().entity(message).build();
	}
	
	@POST
	@Path("/suspenduser/{userId}")
	public Response suspendUser(@PathParam("userId") String userId) {
		String message = adminService.suspendUser(userId);
		return Response.ok().entity(message).build();
	}
	

}
