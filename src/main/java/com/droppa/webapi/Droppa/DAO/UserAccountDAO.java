package com.droppa.webapi.Droppa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;

import com.droppa.webapi.Droppa.common.ClientException;
import com.droppa.webapi.DroppaServices.bean.Person;
import com.droppa.webapi.DroppaServices.bean.UserAccount;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.google.gson.Gson;

import jakarta.ws.rs.core.Response.Status;

public class UserAccountDAO {

	Gson gson = new Gson();

	Connection con;
	Person extractedPerson = new Person();
	ArrayList<UserAccount> users = new ArrayList<>();

	public UserAccountDAO(Connection con) {
		this.con = con;
	}

	public boolean saveAccount(UserAccount userAccount) {
		boolean set = false;

		String owner = gson.toJson(userAccount.getOwner());

		try {
			String check = "select * from users";
			PreparedStatement psc = this.con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(2);
				extractedPerson = gson.fromJson(pers, Person.class);
				if (rs.getString(1).equals(userAccount.getId())) {
					throw new ClientException("User with id '" + userAccount.getId() + "' already exist.");
				}
				if (extractedPerson.getEmail().equals(userAccount.getOwner().getEmail())) {
					throw new ClientException("Email " + userAccount.getOwner().getEmail() + " is already in use.");
				}
				if (extractedPerson.getCelphone().equals(userAccount.getOwner().getCelphone())) {
					throw new ClientException(
							"Celphone number " + userAccount.getOwner().getCelphone() + " is already in use");
				}

			}
			// save useraccount
			String query = "insert into users(id,AccountOwner,IsConfirmed,Otp,Statuses) values(?,?,?,?,?)";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setString(1, userAccount.getId());
			ps.setString(2, owner);
			ps.setBoolean(3, userAccount.isConfirmed());
			ps.setInt(4, userAccount.getOtp());
			ps.setString(5, userAccount.getStatus());
			ps.executeUpdate();
			set = true;
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	public List<UserAccount> getAllUser() {

		try {
			String data = "select * from users";
			PreparedStatement pt = this.con.prepareStatement(data);
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
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	public boolean confirmOTP(String celphone, int otp) {
		boolean set = false;
		try {
			String query = "update users set IsConfirmed=?,statuses=? where statuses=?";
			PreparedStatement pt = this.con.prepareStatement(query);

			pt.setBoolean(1, true);
			pt.setString(2, AccountStatus.ACTIVE.toString());
			pt.setString(3, AccountStatus.AWAITING_CONFIRMATION.toString());

			pt.executeUpdate();
			set = true;
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

}
