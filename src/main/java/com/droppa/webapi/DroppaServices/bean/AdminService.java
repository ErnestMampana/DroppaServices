package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.Auth.AuthenticationService;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.core.BookingStatus;
import com.droppa.webapi.DroppaServices.pojo.Booking;
import com.droppa.webapi.DroppaServices.pojo.DriverAccount;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;
import com.droppa.webapi.DroppaServices.pojo.VehicleDriver;
import com.google.gson.Gson;

@Stateless
@Local
public class AdminService {

	@EJB
	private AuthenticationService authService = new AuthenticationService();
	@EJB
	private BookingService bookingService = new BookingService();
	@EJB
	private UserService userService = new UserService();
	@EJB
	private DriverService driverService = new DriverService();

	private static final Logger logger = Logger.getLogger(AdminService.class.getName());
	private Connection con = MySqlConnection.getConnection();
	Gson gson = new Gson();

	public boolean validateToken(String token) {

		System.out.println("========================== " + authService.extractedToken());

		boolean valid = false;

		try {
			if (token == null || token.trim().equals(""))
				valid = false;

			if (token.equals(authService.extractedToken()))
				valid = true;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return valid;

	}

	public String confirmDriver(String driverId) {
		String message = "Driver not found";

		DriverAccount driver = driverService.getDriverById(driverId);

		if (driver.getStatus() != AccountStatus.AWAITING_CONFIRMATION && driver.getStatus() != AccountStatus.SUSPENDED)
			throw new ClientException("Driver is already confirmed");
		try {

			String update = "update drivers set status=? where id=?";
			PreparedStatement psu = con.prepareStatement(update);
			psu.setString(1, gson.toJson(AccountStatus.ACTIVE));
			psu.setString(2, driverId);
			psu.executeUpdate();
			message = "Driver Activated";
			con.close();

			if (driver.getId() == null)
				throw new ClientException(message);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public String suspendDriver(String driverId) {
		String message = "Driver not found";
		try {

			DriverAccount driverAcc = driverService.getDriverById(driverId);
			if (driverAcc.getStatus().equals(AccountStatus.SUSPENDED))
				throw new ClientException("Driver " + driverAcc.getDriver().getName() + " "
						+ driverAcc.getDriver().getSurname() + " is already suspended");

			String update = "update drivers set status=? where id=?";
			PreparedStatement psu = con.prepareStatement(update);
			psu.setString(1, gson.toJson(AccountStatus.SUSPENDED));
			psu.setString(2, driverId);
			psu.executeUpdate();
			message = "Driver Suspended";
			// con.close();

			if (driverAcc.getId() == null)
				throw new ClientException(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public String suspendUser(String userId) {
		String message = "User not found";
		UserAccount userAcc = userService.getUserById(userId);

		if (userAcc.getId() == null)
			throw new ClientException(message);

		if (userAcc.getStatus() == AccountStatus.SUSPENDED) {
			message = "User " + userAcc.getId() + " is already suspended";
			throw new ClientException("User " + userAcc.getId() + " is already suspended");
		}
		if (userAcc.getStatus().equals(AccountStatus.AWAITING_CONFIRMATION))
			throw new ClientException("User '" + userAcc.getId() + "' not avtivated");
		try {

			String update = "update users set status=? where username=?";
			PreparedStatement psu = con.prepareStatement(update);
			psu.setString(1, gson.toJson(AccountStatus.SUSPENDED));
			psu.setString(2, userId);
			psu.executeUpdate();
			message = "User " + userAcc.getId() + " Suspended";
			// con.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public Booking asignBookingToDriver(String bookingId, String driverId) {

		Booking booking = bookingService.getBookingById(bookingId);

		List<Booking> driverBookings = bookingService.getBookingsByDriverId(driverId);

		if (booking.getBookingId() == null)
			throw new ClientException("Booking with id '" + bookingId + "' does not exist");

		for (int i = 0; i <= driverBookings.size() - 1; i++) {
			if (driverBookings.get(i).getBookingDate().equals(booking.getBookingDate())
					&& driverBookings.get(i).getStatus() == BookingStatus.RESERVED)
				throw new ClientException("Driver has a booking during this time");
		}

		try {

			String update = "update bookings set assignedDriver=?,status=? where bookingId=?";
			PreparedStatement psu = con.prepareStatement(update);
			psu.setString(1, driverId);
			psu.setString(2, gson.toJson(BookingStatus.RESERVED));
			psu.setString(3, bookingId);
			psu.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return booking;
	}

	public String deleteBooking(String bookingId) {
		String message = "Booking not found";
		try {
			String query = "delete from bookings where bookingId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookingId);
			ps.executeUpdate();

			message = "Booking with id '" + bookingId + "' deleted succesfully";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String activateUser(String userId) {
		String message = "user not found";
		UserAccount userAcc = userService.getUserById(message);

		if (userAcc.getStatus().equals(AccountStatus.SUSPENDED)) {
			try {
				String query = "update users set status=? where username=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, gson.toJson(AccountStatus.ACTIVE));
				ps.setString(2, userId);
				ps.executeUpdate();
				message = "User " + userAcc.getId() + " has been re-activated";
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return message;
	}
}
