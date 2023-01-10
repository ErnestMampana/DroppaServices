package com.droppa.webapi.DroppaServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.droppa.webapi.DroppaServices.bean.Person;

public class UserService {

	static HashMap<Integer, Person> userIdMap = getPersonIdMap();

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
		if (userIdMap == null) {
			userIdMap = new HashMap<Integer, Person>();
			// Creating some object of countries while initializing
			Person myself = new Person("this5421thlamjn", "Ernest", "Mampana", "0723568069", "R9000");
			Person someone = new Person("th845421thlamjn", "Barleycan", "Malefo", "0755662321", "R5000");

			userIdMap.put(1, someone);
			userIdMap.put(2, myself);
		}
	}

	public List<Person> getAllUsers() {
		List<Person> users = new ArrayList<Person>(userIdMap.values());
		return users;
	}

	public Person createUserAccount(Person person) {
		userIdMap.put(3, person);
		return person;
	}
	
	public Person updateUserProfile(Person person) {
//		if (Integer.parseInt(person.getId()) <= 0)
//			return null;
		List<Person> users = new ArrayList<Person>(userIdMap.values());
		System.out.println("Ernest ======================== : "+ users.size());
		for(int i = 1 ; i <= users.size(); i++) {
			System.out.println("Ernest ======================== : "+ users.get(i).getId());
			if(users.get(i).getId() == person.getId()) {
				userIdMap.put(1, person);
			}
		}
		
		return person;
	}

	public static HashMap<Integer, Person> getPersonIdMap() {
		return userIdMap;
	}
}
