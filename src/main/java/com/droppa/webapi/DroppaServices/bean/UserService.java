package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.DTO.PersonDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;
import com.google.gson.Gson;

@Stateless
@Local
public class UserService {

	private PartyService partyService = new PartyService();

	private static final Logger logger = Logger.getLogger(UserService.class.getName());

	Gson gson = new Gson();

	Connection con = MySqlConnection.getConnection();
	Person extractedPerson = new Person();
	ArrayList<UserAccount> users = new ArrayList<>();

	public UserService() {
		super();
	}

	public List<UserAccount> getAllUsers() {

		try {
			String data = "select * from users";
			PreparedStatement pt = con.prepareStatement(data);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				UserAccount acc = new UserAccount();
				String pers = rs.getString(2);
				acc.setId(rs.getString(1));
				acc.setOwner(gson.fromJson(pers, Person.class));
				acc.setOtp(rs.getInt(4));
				acc.setConfirmed(rs.getBoolean(3));
				acc.setStatus(gson.fromJson(rs.getString(5), AccountStatus.class));
				users.add(acc);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public PersonDTO createUserAccount(PersonDTO person) {

		String owner = gson.toJson(person);
		String status = gson.toJson(AccountStatus.AWAITING_CONFIRMATION);

		try {
			String check = "select * from users";
			PreparedStatement psc = con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);

				if (extractedPerson.getEmail().equals(person.email)) {
					throw new ClientException("Email " + person.email + " is already in use.");
				}
				if (extractedPerson.getCelphone().equals(person.celphone)) {
					throw new ClientException("Celphone number " + person.celphone + " is already in use");
				}

			}
			// save useraccount
			String query = "insert into users(username,AccountOwner,IsConfirmed,Otp,Password,Status,Token) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, person.email);
			ps.setString(2, owner);
			ps.setBoolean(3, false);
			ps.setInt(4, partyService.generateOTP(person.celphone));
			ps.setString(5, person.password);
			ps.setString(6, status);
			ps.setString(7, partyService.generateToken());
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public Person getUserById(String email) {
		try {
			String query = "select * from users where username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				throw new ClientException("User " + "'" + email + "'" + " does not exist");

			String pers = rs.getString(2);
			extractedPerson = gson.fromJson(pers, Person.class);

			// con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return extractedPerson;
	}

	public String confirmMobile(String celphone, int otp) {

		String message = "user not found";
		String status = gson.toJson(AccountStatus.ACTIVE);

		try {

			String check = "select * from users";
			PreparedStatement psc = con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);

				if (extractedPerson.getCelphone().equals(celphone)) {
					if (rs.getBoolean(3)) {
						message = "Account already activated";
						logger.info(message);
						throw new ClientException(message);
					}
					if (rs.getInt(4) == otp) {
						String query = "update users set IsConfirmed=?,status=? where username=?";
						PreparedStatement pt = this.con.prepareStatement(query);

						pt.setBoolean(1, true);
						pt.setString(2, status);
						pt.setString(3, rs.getString(1));

						pt.executeUpdate();
						message = "User Account Activated";
						con.close();
					} else {
						message = "invalid OTP";
						throw new ClientException("invalid OTP");
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("=============================== " + message);
		return message;
	}

	public String confirmEmail(String email, int otp) {

		String message = "User " + "'" + email + "'" + " was not found";
		String status = gson.toJson(AccountStatus.ACTIVE);

		try {
			String check = "select * from users where username=?";
			PreparedStatement psc = con.prepareStatement(check);
			psc.setString(1, email);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {

				if (rs.getBoolean(3)) {
					message = "Account already activated";
					logger.info(message);
					throw new ClientException(message);
				}
				if (rs.getInt(4) == otp) {
					String query = "update users set IsConfirmed=?,status=? where username=?";
					PreparedStatement pt = this.con.prepareStatement(query);

					pt.setBoolean(1, true);
					pt.setString(2, status);
					pt.setString(3, email);

					pt.executeUpdate();
					message = "User Account Activated";
					con.close();
				} else {
					message = "invalid OTP";
					throw new ClientException("invalid OTP");
				}

			}

			if (!rs.next())
				throw new ClientException(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("=============================== : " + message);
		return message;
	}

}
