package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.Droppa.DTO.PersonDTO;
import com.droppa.webapi.Droppa.common.ClientException;
import com.droppa.webapi.Droppa.common.MySqlConnection;
import com.droppa.webapi.Droppa.pojo.Person;
import com.droppa.webapi.Droppa.pojo.UserAccount;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
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
				acc.setStatus(rs.getString(5));
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

		try {
			String check = "select * from users";
			PreparedStatement psc = con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);
				if (rs.getString(1).equals(person.id)) {
					throw new ClientException("User with id '" + person.id + "' already exist.");
				}
				if (extractedPerson.getEmail().equals(person.email)) {
					throw new ClientException("Email " + person.email + " is already in use.");
				}
				if (extractedPerson.getCelphone().equals(person.celphone)) {
					throw new ClientException("Celphone number " + person.celphone + " is already in use");
				}

			}
			// save useraccount
			String query = "insert into users(id,AccountOwner,IsConfirmed,Otp,Statuses) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, person.id);
			ps.setString(2, owner);
			ps.setBoolean(3, false);
			ps.setInt(4, partyService.generateOTP(person.celphone));
			ps.setString(5, AccountStatus.AWAITING_CONFIRMATION.toString());
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public Person getUserById(String id) {
		try {
			String query = "select * from users where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return extractedPerson;
	}

	public String confirmMobile(String celphone, int otp) {
		String message = "user not found";
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
					System.out.println("================== " + rs.getInt(4));
					if (rs.getInt(4) == otp) {
						String query = "update users set IsConfirmed=?,statuses=? where statuses=?";
						PreparedStatement pt = this.con.prepareStatement(query);

						pt.setBoolean(1, true);
						pt.setString(2, AccountStatus.ACTIVE.toString());
						pt.setString(3, AccountStatus.AWAITING_CONFIRMATION.toString());

						pt.executeUpdate();
						message = "User Account Activated";
						con.close();
					}

				}

			}
			logger.info("=============================== User not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String confirmEmail(String email, int otp) {
		String message = "user not found";
		try {

			String check = "select * from users";
			PreparedStatement psc = con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);

				if (extractedPerson.getEmail().equals(email)) {
					if (rs.getBoolean(3)) {
						message = "Account already activated";
						logger.info("Account already activated");
						throw new ClientException("Account already activated");
					}
					if (rs.getInt(4) == otp) {
						String query = "update users set IsConfirmed=?,statuses=? where statuses=?";
						PreparedStatement pt = con.prepareStatement(query);

						pt.setBoolean(1, true);
						pt.setString(2, AccountStatus.ACTIVE.toString());
						pt.setString(3, AccountStatus.AWAITING_CONFIRMATION.toString());

						pt.executeUpdate();
						message = "User Account Activated";
						con.close();
					}

				}

			}
			logger.info("=============================== User not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

}
