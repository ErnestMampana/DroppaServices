package com.droppa.webapi.DroppaServices.restApi;

import javax.ejb.EJB;
import com.droppa.webapi.Droppa.DTO.BookingDTO;
import com.droppa.webapi.Droppa.pojo.Booking;
import com.droppa.webapi.DroppaServices.bean.BookingService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingsRestApi {
	
	@EJB
	private BookingService bookingService = new BookingService();
	
	@POST
	@Path("/book")
	public Response createBooking(BookingDTO bookingDto) {
		Booking book = bookingService.createBooking(bookingDto);
		return Response.ok().entity(bookingDto).build();
	}
	
	@GET
	@Path("/getAllBokings")
	public Response getAllBookings() {
		return Response.ok().entity(bookingService).build();
	}
//	
//	@GET
//	@Path("/bookingById/{id}")
//	public Response getBookingById(@PathParam("id") String id) {
//		return Response.ok().entity(id).build();
//	}

}
