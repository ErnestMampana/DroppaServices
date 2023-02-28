package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.Auth.AuthenticationService;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.pojo.Booking;
import com.droppa.webapi.DroppaServices.pojo.VehicleDriver;
import com.google.gson.Gson;

@Stateless
@Local
public class AdminService {

	@EJB
	private AuthenticationService authService = new AuthenticationService();
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
		try {
			String query = "select * from drivers where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, driverId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (gson.fromJson(rs.getString(4), AccountStatus.class) != AccountStatus.AWAITING_CONFIRMATION
						&& gson.fromJson(rs.getString(4), AccountStatus.class) != AccountStatus.SUSPENDED)
					throw new ClientException("Driver is already confirmed");
				String update = "update drivers set status=? where id=?";
				PreparedStatement psu = con.prepareStatement(update);
				psu.setString(1, gson.toJson(AccountStatus.ACTIVE));
				psu.setString(2, driverId);
				psu.executeUpdate();
				message = "Driver Activated";
				con.close();
			}

			if (!rs.next())
				throw new ClientException(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public String suspendDriver(String driverId) {
		String message = "Driver not found";
		try {
			String query = "select * from drivers where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, driverId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (gson.fromJson(rs.getString(4), AccountStatus.class) == AccountStatus.SUSPENDED)
					throw new ClientException("Driver is already suspended");
				String update = "update drivers set status=? where id=?";
				PreparedStatement psu = con.prepareStatement(update);
				psu.setString(1, gson.toJson(AccountStatus.SUSPENDED));
				psu.setString(2, driverId);
				psu.executeUpdate();
				message = "Driver Suspended";
				// con.close();
			}

			if (!rs.next())
				throw new ClientException(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

//	public Booking asignBookingToDriver(String bookingId, String driverId) {
//		try {
//			String check = "select * from bookings where assignedDriver=?";
//			PreparedStatement psc = con.prepareStatement(check);
//			psc.setString(1, driverId);
//			ResultSet rs = psc.executeQuery();
//
//			// String checkB = "";
//
//			while (rs.next()) {
//				// check if the driver has a booking matching the time of the new booking
//				if(rs.)
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return booking;
//	}

}
