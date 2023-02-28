package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.DTO.BookingDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.BookingStatus;
import com.droppa.webapi.DroppaServices.pojo.Adress;
import com.droppa.webapi.DroppaServices.pojo.Booking;
import com.droppa.webapi.DroppaServices.pojo.DropDetails;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.google.gson.Gson;

@Stateless
@Local
public class BookingService {
	private static final Logger logger = Logger.getLogger(BookingService.class.getName());

	Gson gson = new Gson();

	ArrayList<Booking> bookings = new ArrayList<>();

	Connection con = MySqlConnection.getConnection();

	Booking extractedBooking = new Booking();

	@EJB
	private UserService userService = new UserService();
	private PartyService partyService = new PartyService();

	public BookingService() {
		super();

	}

	public Booking createBooking(BookingDTO bookingDto) {

		String bookingId;

		Person person = userService.getUserById(bookingDto.userId);

//		if (person.getId() == null)
//			throw new ClientException("Only registered users can create a booking");

		Adress pickupAdress = new Adress(bookingDto.pickUpStandNumber, bookingDto.pickUpStreetName,
				bookingDto.pickUpSuburb, bookingDto.pickUpProvince, bookingDto.pickUpPostalCode);

		Adress dropoffAdress = new Adress(bookingDto.dropOffStandNumber, bookingDto.dropOffStreetName,
				bookingDto.dropOffSuburb, bookingDto.dropOffProvince, bookingDto.dropOffPostalCode);

		DropDetails pickUpDetails = new DropDetails(bookingDto.pickUpName, bookingDto.pickUpSurname,
				bookingDto.pickUpContact);

		DropDetails dropOffDetails = new DropDetails(bookingDto.dropOffName, bookingDto.dropOffSurname,
				bookingDto.dropOffContact);

		bookingId = partyService.randomChars(15);

		boolean found = true;

		while (found) {
			try {
				String check = "select * from bookings";
				PreparedStatement ps = con.prepareStatement(check);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getString(1).equals(bookingId))
						bookingId = partyService.randomChars(15);
					logger.info("================================= " + bookingId);
				}

				found = false;
				// con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Booking booking = new Booking(bookingId, pickupAdress, dropoffAdress, bookingDto.userId, pickUpDetails,
				dropOffDetails, bookingDto.date, 0, "No Driver", BookingStatus.AWAITING_DRIVER);

		try {
			String pickUpAdress = gson.toJson(booking.getPickupAdress());
			String dropOffAdress = gson.toJson(booking.getDropOffAdress());
			String pickUpDetail = gson.toJson(booking.getPickupDetails());
			String dropOffDetail = gson.toJson(booking.getDropOffDetails());

			String query = "insert into bookings(bookingId,pickupAdress,dropOffAdress,userId,pickupDetails,dropOffDetails,bookingDate,price,assignedDriver,status) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, booking.getBookingId());
			ps.setString(2, pickUpAdress);
			ps.setString(3, dropOffAdress);
			ps.setString(4, booking.getUserId());
			ps.setString(5, pickUpDetail);
			ps.setString(6, dropOffDetail);
			ps.setString(7, booking.getBookingDate());
			ps.setDouble(8, booking.getPrice());
			ps.setString(9, booking.getAssinedDriver());
			ps.setString(10, gson.toJson(booking.getStatus()));

			ps.executeUpdate();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return booking;
	}

	public Booking getBookingById(String id) {
		try {
			String query = "select * from bookings where bookingId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				extractedBooking.setBookingId(rs.getString(1));
				extractedBooking.setPickupAdress(gson.fromJson(rs.getString(2), Adress.class));
				extractedBooking.setDropOffAdress(gson.fromJson(rs.getString(3), Adress.class));
				extractedBooking.setUserId(rs.getString(4));
				extractedBooking.setPickupDetails(gson.fromJson(rs.getString(5), DropDetails.class));
				extractedBooking.setDropOffDetails(gson.fromJson(rs.getString(6), DropDetails.class));
				extractedBooking.setPrice(7);

			}

			if (!rs.next())
				throw new ClientException("===== Booking does not exist =====");

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(extractedBooking);
		return extractedBooking;
	}

	public List<Booking> getBookingsByStatus(String status) {
		try {
			String query = "select * from bookings where status=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String pickUpAdress = rs.getString(2);
				String dropOffAdress = rs.getString(3);
				String pickUpDetails = rs.getString(5);
				String dropOffDetails = rs.getString(6);

				Booking book = new Booking();

				book.setBookingId(rs.getString(1));
				book.setPickupAdress(gson.fromJson(pickUpAdress, Adress.class));
				book.setDropOffAdress(gson.fromJson(dropOffAdress, Adress.class));
				book.setUserId(rs.getString(4));
				book.setPickupDetails(gson.fromJson(pickUpDetails, DropDetails.class));
				book.setDropOffDetails(gson.fromJson(dropOffDetails, DropDetails.class));
				book.setPrice(Double.parseDouble(rs.getString(7)));

				bookings.add(book);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}

	public List<Booking> getBookingsByStatusForUser(String status, String userId) {
		try {
			String query = "select * from bookings where status=? and userId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, status);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String pickUpAdress = rs.getString(2);
				String dropOffAdress = rs.getString(3);
				String pickUpDetails = rs.getString(5);
				String dropOffDetails = rs.getString(6);

				Booking book = new Booking();

				book.setBookingId(rs.getString(1));
				book.setPickupAdress(gson.fromJson(pickUpAdress, Adress.class));
				book.setDropOffAdress(gson.fromJson(dropOffAdress, Adress.class));
				book.setUserId(rs.getString(4));
				book.setPickupDetails(gson.fromJson(pickUpDetails, DropDetails.class));
				book.setDropOffDetails(gson.fromJson(dropOffDetails, DropDetails.class));
				book.setPrice(Double.parseDouble(rs.getString(7)));

				bookings.add(book);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}

	public List<Booking> getAllBookings() {

		try {
			String query = "select * from bookings";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String pickUpAdress = rs.getString(2);
				String dropOffAdress = rs.getString(3);
				String pickUpDetails = rs.getString(5);
				String dropOffDetails = rs.getString(6);

				Booking book = new Booking();

				book.setBookingId(rs.getString(1));
				book.setPickupAdress(gson.fromJson(pickUpAdress, Adress.class));
				book.setDropOffAdress(gson.fromJson(dropOffAdress, Adress.class));
				book.setUserId(rs.getString(4));
				book.setPickupDetails(gson.fromJson(pickUpDetails, DropDetails.class));
				book.setDropOffDetails(gson.fromJson(dropOffDetails, DropDetails.class));
				book.setPrice(Double.parseDouble(rs.getString(7)));

				bookings.add(book);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(bookings);
		return bookings;
	}

}
