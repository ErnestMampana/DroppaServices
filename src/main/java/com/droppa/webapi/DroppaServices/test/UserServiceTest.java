package com.droppa.webapi.DroppaServices.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.droppa.webapi.DroppaServices.DTO.PersonDTO;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;
import com.droppa.webapi.DroppaServices.restApi.UserRestApi;

import jakarta.ws.rs.core.Response;

class UserServiceTest {

	private UserRestApi restApi = new UserRestApi();
	private static final Logger logger = Logger.getLogger(UserServiceTest.class.getName());

	// @Test
	void testUserService() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllUsers() {
		Response users = restApi.getAllUsers();
		@SuppressWarnings("unchecked")
		List<UserAccount> user = (List<UserAccount>) users.getEntity();

//		for (int i = 0; i <= user.size(); i++) {
//			
//			System.out.println(user.get(i).getId());
//			
//		}
	}

	@Test
	void testCreateUserAccount() {

		PersonDTO personDto = new PersonDTO();

		personDto.userName = "Ernest";
		personDto.surname = "Mampana";
		personDto.celphone = "0785436214";
		personDto.walletBalance = "R8996";
		personDto.email = "ernet@gmail.com";
		personDto.password = "thatomohlala";

		restApi.createUser(personDto);
	}

	@Test
	void testGetUserById() {
		String id = "ernest@gmail.com";
		logger.info("========================= getting user");
		restApi.getUserById(id).getEntity();
		logger.info(restApi.getUserById(id).getEntity().toString());
	}

	@Test
	void testConfirmMobile() {
		int code = 35170;
		String celphone = "0785436214";
		Response resp = restApi.confirmMobile(code, celphone);
		System.out.println(resp.getEntity());

	}

	@Test
	void testConfirmEmail() {
		int code = 21512;
		String email = "ernest@gmail.com";
		Response resp = restApi.confirmEmail(code, email);
		System.out.println(resp.getEntity());
	}

}
