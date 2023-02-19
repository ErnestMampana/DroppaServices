package com.droppa.webapi.DroppaServices;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.sql.ConnectionPoolDataSource;

import com.droppa.webapi.Droppa.DAO.MySqlConnection;
import com.droppa.webapi.Droppa.DAO.UserAccountDAO;
import com.droppa.webapi.Droppa.common.ClientException;
import com.droppa.webapi.DroppaServices.bean.Person;
import com.droppa.webapi.DroppaServices.bean.UserAccount;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.google.gson.Gson;

@Stateless
@Local
public class UserService {

	static HashMap<Integer, UserAccount> userIdMap = getPersonIdMap();
	UserAccount dummyUser = new UserAccount();
	PartyService party = new PartyService();
	UserAccountDAO userDAO = new UserAccountDAO(MySqlConnection.getConnection());
	private static final Logger logger = Logger.getLogger(UserService.class.getName());
	
	Gson gson = new Gson();

	Connection con;
	Person extractedPerson = new Person();
	ArrayList<UserAccount> users = new ArrayList<>();

	public UserService(Connection con) {
		this.con = con;
	}

	public UserService() {
		super();
//		// TODO Auto-generated constructor stub
//		if (userIdMap == null) {
//			userIdMap = new HashMap<Integer, UserAccount>();
//			// Creating some object of countries while initializing
//
//			Person myself = new Person("this5421thlamjn", "Ernest", "Mampana", "0723568069", "R9000",
//					"ernest@gmail.com");
//			// Person someone = new Person("th845421thlamjn", "Barleycan", "Malefo",
//			// "0755662321", "R5000");
//
//			UserAccount myAccount = new UserAccount(myself, true, 0, AccountStatus.ACTIVE.toString());
//
//			userIdMap.put(1, myAccount);
//			// userIdMap.put(2, myself);
//		}
	}

	public List<UserAccount> getAllUsers() {
		List<UserAccount> users = new ArrayList<UserAccount>(userDAO.getAllUser());
		return users;
	}

	public Person createUserAccount(Person person) {
		UserAccount userAcc = new UserAccount(person.getId(),person, false, party.generateOTP(person.getCelphone()),
				AccountStatus.AWAITING_CONFIRMATION.toString());
		userDAO.saveAccount(userAcc);
		return person;
	}

	public Person createUsersAccount(Person person) {
		List<UserAccount> users = new ArrayList<UserAccount>(userIdMap.values());
		for (int i = 1; i <= users.size() - 1; i++) {
			System.out.println(person.getCelphone().equals(users.get(i).getOwner().getCelphone()));
			if (person.getCelphone().equals(users.get(i).getOwner().getCelphone())) {
				throw new ClientException("User with cellphone number '" + person.getCelphone() + "' already exist.");
			}
			if (person.getEmail().equals(users.get(i).getOwner().getEmail())) {
				throw new ClientException("User with email '" + person.getEmail() + "' already exist.");
			}
		}

		if (person.getCelphone().isBlank()) {
			logger.info("Cellphone cannot be null");
			throw new ClientException("Cellphone cannot be null");
		} else {
			dummyUser.setOwner(person);
			dummyUser.setConfirmed(false);
			dummyUser.setOtp(party.generateOTP(person.getCelphone()));
			dummyUser.setStatus(AccountStatus.AWAITING_CONFIRMATION.toString());
			userIdMap.put(2, dummyUser);
		}

		return person;
	}

	public UserAccount updateUserProfile(UserAccount person) {
//		if (Integer.parseInt(person.getId()) <= 0)
//			return null;
		List<UserAccount> users = new ArrayList<UserAccount>(userIdMap.values());
		System.out.println("Ernest ======================== : " + users.size());
		for (int i = 1; i <= users.size(); i++) {
			System.out.println("Ernest ======================== : " + users.get(i).getOwner().getId());
			if (users.get(i).getOwner().getId() == person.getOwner().getId()) {
				userIdMap.put(1, person);
			}
		}

		return person;
	}

	public String confirmMobile(String celphone, int otp) {
		String message = "Invalid OTP";
		boolean updated = false;
		updated = userDAO.confirmOTP(celphone, otp);
		if(updated) {
			message = "User Account Activated";
		}
		
		return message;

	}

	public static HashMap<Integer, UserAccount> getPersonIdMap() {
		return userIdMap;
	}
}
