package com.droppa.webapi.DroppaServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.bean.Person;
import com.droppa.webapi.DroppaServices.bean.UserAccount;
import com.droppa.webapi.DroppaServices.core.AccountStatus;

@Stateless
@Local
public class UserService {

	static HashMap<Integer, UserAccount> userIdMap = getPersonIdMap();
	UserAccount dummyUser = new UserAccount();
	PartyService party = new PartyService();
	private static final Logger logger = Logger.getLogger(UserService.class.getName());

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
		if (userIdMap == null) {
			userIdMap = new HashMap<Integer, UserAccount>();
			// Creating some object of countries while initializing

			Person myself = new Person("this5421thlamjn", "Ernest", "Mampana", "0723568069", "R9000");
			//Person someone = new Person("th845421thlamjn", "Barleycan", "Malefo", "0755662321", "R5000");

			UserAccount myAccount = new UserAccount(myself, true, 0,AccountStatus.ACTIVE.toString() );

			userIdMap.put(1, myAccount);
			// userIdMap.put(2, myself);
		}
	}

	public List<UserAccount> getAllUsers() {
		List<UserAccount> users = new ArrayList<UserAccount>(userIdMap.values());
		return users;
	}

	
	public Person createUserAccount(Person person) {

		if (person.getCelphone().isBlank()) {
			logger.info("Ernest Loggs ===================== Cellphone cannot be null");
		} else {
			dummyUser.setOwner(person);
			dummyUser.setConfirmed(false);
			dummyUser.setOtp(party.generateOTP(person.getCelphone()));
			dummyUser.setStatus(AccountStatus.AWAITING_CONFIRMATION.toString());
			userIdMap.put(2, dummyUser);
			System.out.println("Ernest Loggs ===================== user " + person.getUserName() + " has been created");
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
			if (users.get(i).getOwner().getId() == person.owner.getId()) {
				userIdMap.put(1, person);
			}
		}

		return person;
	}

	public void confirmMobile(String celphone, int otp) {
		System.out.println("******************* " + celphone + "%%%%%%%%%%%%%%%%%%%% "+otp);
		List<UserAccount> users = new ArrayList<UserAccount>(userIdMap.values());
		for (int i = 1; i <= users.size() - 1; i++) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^ "+users.get(i).getOwner().getCelphone());
			if (users.get(i).getOwner().getCelphone().equals(celphone)) {
				if (otp == users.get(i).getOtp()) {
					dummyUser.setConfirmed(true);
					dummyUser.setOwner(users.get(i).getOwner());
					dummyUser.setStatus(AccountStatus.ACTIVE.toString());
					userIdMap.put(2, dummyUser);
					logger.info("===================== User Confirmed ===================");
				}
			}
		}

	}

	public static HashMap<Integer, UserAccount> getPersonIdMap() {
		return userIdMap;
	}
}
