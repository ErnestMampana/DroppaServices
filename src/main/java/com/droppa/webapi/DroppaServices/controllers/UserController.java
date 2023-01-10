package com.droppa.webapi.DroppaServices.controllers;

import java.util.List;

import com.droppa.webapi.DroppaServices.CountryService;
import com.droppa.webapi.DroppaServices.UserService;
import com.droppa.webapi.DroppaServices.bean.Person;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
public class UserController {

	UserService userService = new UserService();

	@GET
	@Path("/getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getUser(@PathParam("id") String id) {
		return userService.createUserAccount(null);
	}

	@GET
	@Path("/viewallusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllUsers() {

		List<Person> users = userService.getAllUsers();
		//Entity entity = users;

		System.out.println("Ernest ============= " + Response.ok().entity(users).build().toString());
		
		Response res = Response.ok().entity(users).build();
		
	    //System.out.println(res.getEntity().toString());	

		return users;

	}
	
	
	@POST
	@Path("/createuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Person createUser(Person person) {
		if(person.getCelphone().isEmpty()) {
			System.out.println("Error ============== : Empty field are not allowed");
			return null;
		}
		return userService.createUserAccount(person);
		//return Response.ok().build();
	}
	
	@PUT
	@Path("/updateprofile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Person updateUserProfile(Person person) {
		return userService.updateUserProfile(person);
	}
	

}
