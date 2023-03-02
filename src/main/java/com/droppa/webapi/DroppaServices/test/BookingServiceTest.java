///**
// * 
// */
//package com.droppa.webapi.DroppaServices.test;
//
////import static org.junit.jupiter.api.Assertions.*;
//
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//
////import org.junit.jupiter.api.Test;
//
//import com.droppa.webapi.DroppaServices.DTO.BookingDTO;
//import com.droppa.webapi.DroppaServices.pojo.Booking;
//import com.droppa.webapi.DroppaServices.restApi.BookingsRestApi;
//
//import jakarta.ws.rs.core.Response;
//import org.junit.Test;
//
///**
// * @author Ernest Mampana
// *
// */
//class BookingServiceTest {
//	BookingsRestApi bookingRest = new BookingsRestApi();
//	private static final SecureRandom secureRandom = new SecureRandom();
//	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
//
//	// @Test
//	void testCreateBooking() {
//
//		BookingDTO bookingDto = new BookingDTO();
//
//		bookingDto.userId = "ernet@gmail.com";
//
//		bookingDto.pickUpStandNumber = 582;
//		bookingDto.pickUpStreetName = "Mehlulo Street";
//		bookingDto.pickUpSuburb = "Tembisa";
//		bookingDto.pickUpProvince = "Gauteng";
//		bookingDto.pickUpPostalCode = 1632;
//
//		bookingDto.dropOffStandNumber = 5099;
//		bookingDto.dropOffStreetName = "Montjane Street";
//		bookingDto.dropOffSuburb = "Tembisa";
//		bookingDto.dropOffProvince = "Gauteng";
//		bookingDto.dropOffPostalCode = 1632;
//
//		bookingDto.pickUpName = "Ernest";
//		bookingDto.pickUpSurname = "Mampana";
//		bookingDto.pickUpContact = "0723568069";
//
//		bookingDto.dropOffName = "Ernest";
//		bookingDto.dropOffSurname = "Mampana";
//		bookingDto.dropOffContact = "0723568069";
//
//		bookingDto.date = "12/04/2023";
//
//		bookingRest.createBooking(bookingDto);
//	}
//
//	// @Test
//	void getAllBookings() {
//		Response resp = bookingRest.getAllBookings();
//		@SuppressWarnings("unchecked")
//		ArrayList<Booking> bookings = (ArrayList<Booking>) resp.getEntity();
//		for (int i = 0; i <= bookings.size() - 1; i++) {
//			String bookingId = bookings.get(i).getBookingId();
//			System.out.println("==================================== : " + bookingId);
//		}
//		resp.getStatus();
//		System.out.println(resp.getEntity());
//		System.out.println(resp.getStatus());
//	}
//
//	// @Test
//	void getBookingById() {
//		String id = "lWvpZee9CJFpP6O";
//		Response resp = bookingRest.getBookingById(id);
//		Booking booking = (Booking) resp.getEntity();
//		booking.getBookingId();
//		System.out.println("========================= : " + booking.getDropOffAdress().getStreetName());
//
//	}
//
//	// @Test
//	void getBookingByDriverId() {
//		String id = "th847991thlamjn";
//		Response resp = bookingRest.getBookingByDriverId(id);
//		List<Booking> bookings = (List<Booking>) resp.getEntity();
//		for (int i = 0; i <= bookings.size(); i++) {
//			String bookingId = bookings.get(i).getBookingId();
//			System.out.println("==================================== : " + bookingId);
//		}
//		System.out.println("========================= : " + resp.getEntity());
//
//	}
//
//	//@Test
//	void TestGetBookingByUserId() {
//		String id = "ernet@gmail.com";
//		Response resp = bookingRest.getBookingByUserId(id);
//		List<Booking> bookings = (List<Booking>) resp.getEntity();
//		for (int i = 0; i <= bookings.size() - 1; i++) {
//			String bookingId = bookings.get(i).getBookingId();
//			System.out.println("==================================== : " + bookingId);
//		}
//		System.out.println("========================= : " + resp.getEntity());
//
//	}
//
//	@Test
//	void TestCancelBooking() {
//		String id = "ernet@gmail.com";
//		String bookingId = "tc2JrzBenhnX6Bq";
//		Response resp = bookingRest.cancelBooking(bookingId, id);
//		Booking booking = (Booking) resp.getEntity();
//		System.out.println("========================= : " + booking.getBookingId());
//
//	}
//
//}
