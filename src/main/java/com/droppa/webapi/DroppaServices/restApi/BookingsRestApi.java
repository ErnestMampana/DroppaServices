//package com.droppa.webapi.DroppaServices.restApi;
//
//import java.util.List;
//
//import javax.ejb.EJB;
//
//import com.droppa.webapi.DroppaServices.Auth.Secured;
//import com.droppa.webapi.DroppaServices.DTO.BookingDTO;
//import com.droppa.webapi.DroppaServices.bean.BookingService;
//import com.droppa.webapi.DroppaServices.pojo.Booking;
//
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.GET;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.PUT;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.PathParam;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//
//@Path("bookings")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class BookingsRestApi {
//	
//	@EJB
//	private BookingService bookingService = new BookingService();
//	
//	//@Secured
//	@POST
//	@Path("/book")
//	public Response createBooking(BookingDTO bookingDto) {
//		Booking book = bookingService.createBooking(bookingDto);
//		return Response.ok().entity(book).build();
//	}
//	
//	@GET
//	@Path("/getAllBokings")
//	public Response getAllBookings() {
//		List<Booking> bookings = bookingService.getAllBookings();
//		return Response.ok().entity(bookings).build();
//	}
//	
//	@GET
//	@Path("/bookingById/{id}")
//	public Response getBookingById(@PathParam("id") String id) {
//		Booking booking = bookingService.getBookingById(id);
//		return Response.ok().entity(booking).build();
//	}
//	
//	@GET
//	@Path("/bookingByDriverId/{id}")
//	public Response getBookingByDriverId(@PathParam("id") String id) {
//		List<Booking> bookings = bookingService.getBookingsByDriverId(id);
//		return Response.ok().entity(bookings).build();
//	}
//	
//	@GET
//	@Path("/bookingByDriverId/{id}")
//	public Response getBookingByUserId(@PathParam("id") String id) {
//		List<Booking> bookings = bookingService.getBookingsByUserId(id);
//		return Response.ok().entity(bookings).build();
//	}
//	
//	@PUT
//	@Path("/cancelBooking{bookingId}/{userId}")
//	public Response cancelBooking(@PathParam("bookingId") String bookingId,@PathParam("userId") String userId) {
//		Booking cBooking = bookingService.cancelBooking(bookingId, userId);
//		return Response.ok().entity(cBooking).build();
//	}
//
//}
