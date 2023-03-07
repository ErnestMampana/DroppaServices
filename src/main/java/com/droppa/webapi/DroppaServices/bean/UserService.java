package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.Auth.CredentialsDTO;
import com.droppa.webapi.DroppaServices.DTO.PersonDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;
import com.google.gson.Gson;
import com.mysql.cj.protocol.AuthenticationProvider;

import jakarta.inject.Inject;

@Stateless
@Local
public class UserService {

	@EJB
	PartyService partyService = new PartyService();
	@SuppressWarnings("rawtypes")
	@Inject
	private AuthenticationProvider provider;

	private static final Logger logger = Logger.getLogger(UserService.class.getName());

	Gson gson = new Gson();

	Connection con = MySqlConnection.getConnection();
	Person extractedPerson = new Person();
	ArrayList<UserAccount> users = new ArrayList<>();
	UserAccount extractedAccount = new UserAccount();

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

				logger.info("========================== Fetching all users");

				users.add(acc);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public PersonDTO createUserAccount(PersonDTO person) {

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
			ps.setString(2, gson.toJson(person));
			ps.setBoolean(3, false);
			ps.setInt(4, partyService.generateOTP(person.celphone));
			ps.setString(5, person.password);
			ps.setString(6, gson.toJson(AccountStatus.AWAITING_CONFIRMATION));
			ps.setString(7, partyService.generateToken());
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public UserAccount getUserById(String email) {
		try {
			String query = "select * from users where username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				throw new ClientException("User " + "'" + email + "'" + " does not exist");

			extractedAccount.setId(rs.getString(1));
			extractedAccount.setOwner(gson.fromJson(rs.getString(2), Person.class));
			extractedAccount.setConfirmed(rs.getBoolean(3));
			extractedAccount.setStatus(gson.fromJson(rs.getString(6), AccountStatus.class));
			extractedAccount.setOtp(rs.getInt(4));
			extractedAccount.setPassword(rs.getString(5));
			extractedAccount.setConfirmed(rs.getBoolean(3));

			// con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return extractedAccount;
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

		UserAccount userAcc = getUserById(email);
		if (userAcc.getId() == null)
			throw new ClientException(message);

		if (userAcc.getStatus().equals(AccountStatus.ACTIVE)) {
			message = "Account already activated";
			logger.info("======================= " + message);
			throw new ClientException(message);
		}

		try {

			if (userAcc.getOtp() == otp) {
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
				logger.info("========================= " + message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("=============================== : " + message);
		return message;
	}

	public int requestPasswordReset(String username) {
		int otp = 0;
		try {
			UserAccount userAccount = getUserById(username);

			if (userAccount.getId().equals(username)) {

				otp = partyService.generateOTP(userAccount.getOwner().getCelphone());

				String query = "update users set isConfirmed=?,otp=?,status=? where username=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setBoolean(1, false);
				ps.setInt(2, otp);
				ps.setString(3, gson.toJson(AccountStatus.AWAITING_PWD_RESET));
				ps.setString(4, username);

				ps.executeUpdate();

				con.close();

			} else {
				throw new ClientException("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otp;
	}

	public UserAccount resetPassword(int otp, String username) {
		UserAccount userAcc = getUserById(username);
		try {
			if (userAcc.getStatus().equals(AccountStatus.AWAITING_PWD_RESET)) {
				if (getUserOtp(username) == otp) {

					String query = "update users set status=? where username=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, gson.toJson(AccountStatus.ACTIVE));
					ps.setString(2, username);
					ps.executeUpdate();
					extractedAccount = getUserById(username);
					extractedAccount = autoUserLogin(username);

				} else {
					throw new ClientException("Invalid OTP");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return extractedAccount;
	}

	public int getUserOtp(String username) {

		int otp = 0;

		try {
			String query = "select otp from users where username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				otp = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return otp;
	}

	
//	public void logout(CredentialsDTO credentialsDto) {
//        if (provider.isValid(credentialsDto.token)) {
//        	credentialsDto.token = null; //discard token
//            return loginDTO;
//        }
//        throw new ClientException("Bad request"); //don't give too much detail to client here!
//    }

	public UserAccount autoUserLogin(String username) {

		users = (ArrayList<UserAccount>) getAllUsers();
		for (int i = 0; i <= users.size() - 1; i++) {
			if (users.get(i).getId().equals(username)) {
				extractedAccount.setId(users.get(i).getId());
				extractedAccount.setOwner(users.get(i).getOwner());
				extractedAccount.setStatus(users.get(i).getStatus());
				extractedAccount.setToken(users.get(i).getToken());

			} else {
				throw new ClientException("Auto login failed");
			}
		}
		return extractedAccount;
	}

}
