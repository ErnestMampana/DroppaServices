package com.droppa.webapi.DroppaServices.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.droppa.webapi.Droppa.DTO.PersonDTO;
import com.droppa.webapi.DroppaServices.restApi.UserRestApi;

import jakarta.ws.rs.core.Response;

class UserServiceTest {

	private UserRestApi restApi = new UserRestApi();
	private static final Logger logger = Logger.getLogger(UserServiceTest.class.getName());

	@Test
	void testUserService() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllUsers() {
		Response users = restApi.getAllUsers();
	}

	@Test
	void testCreateUserAccount() {

		PersonDTO personDto = new PersonDTO();

		personDto.id = "yyfb67s9vkak009";
		personDto.userName = "Ernest";
		personDto.surname = "Mampana";
		personDto.celphone = "0745536289";
		personDto.walletBalance = "R8996";
		personDto.email = "ern@gmail.com";
		restApi.createUser(personDto);
	}

	@Test
	void testGetUserById() {
		String id = "th847451thlamjn";
		logger.info("========================= getting user");
		restApi.getUserById(id);
	}

	@Test
	void testConfirmMobile() {
		fail("Not yet implemented");
	}

	@Test
	void testConfirmEmail() {
		fail("Not yet implemented");
	}

}
