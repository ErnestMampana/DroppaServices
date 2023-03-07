///**
// * 
// */
//package com.droppa.webapi.DroppaServices.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//
//import org.junit.jupiter.api.Test;
//
//import com.droppa.webapi.DroppaServices.pojo.Booking;
//import com.droppa.webapi.DroppaServices.restApi.AdminRestApi;
//
//import jakarta.ws.rs.core.Response;
//
//
///**
// * @author Ernest Mampana
// *
// */
//class AdminServiceTest {
//	
//	private AdminRestApi adminRest = new AdminRestApi();
//
//	/**
//	 * Test method for {@link com.droppa.webapi.DroppaServices.restApi.AdminRestApi#suspendDriver(java.lang.String)}.
//	 */
//	//@Test
//	void testSuspendDriver() {
//		String driverId = "th847991thlamjn";
//		Response resp = adminRest.suspendDriver(driverId);
//		System.out.println(resp.getEntity());
//	}
//
//	/**
//	 * Test method for {@link com.droppa.webapi.DroppaServices.restApi.AdminRestApi#activateDriver(java.lang.String)}.
//	 */
//	//@Test
//	void testActivateDriver() {
//		String driverId = "th847991thlamjn";
//		Response resp = adminRest.activateDriver(driverId);
//		System.out.println(resp.getEntity());
//	}
//	
//	//@Test
//	void testAsignBookingToDriver() {
//		String driverId = "th847991thlamjn";
//		String bookingId = "tc2JrzBenhnX6Bq";
//		Response resp = adminRest.asignBookingToDriver(bookingId, driverId);
//		Booking booking = (Booking) resp.getEntity();
//		System.out.println(booking.getDropOffAdress().getPostalCode());
//	}
//	
//	//@Test
//	void testDeleteBookingService() {
//		String bookingId = "tc2JrzBenhnX6Bq";
//		Response resp = adminRest.deleteBooking(bookingId);
//		String message = resp.getEntity().toString();
//	 
//		Calendar cal = Calendar.getInstance();
//		
//		Date dat = new Date();
//
//		String dateTimeString = "2014-01-16T10:25:00";
//	    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
//	    LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
//	    
//		//date.getDate();
//		System.out.println("============ : "+dat.getDate());
//	}
//	
//	@Test
//	void testSuspendUser() {
//		String userId = "ernet@gmail.com";
//		Response resp = adminRest.suspendUser(userId);
//		System.out.println("================== "+resp.getEntity().toString());
//	}
//
//}
