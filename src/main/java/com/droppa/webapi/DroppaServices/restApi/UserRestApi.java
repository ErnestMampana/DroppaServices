package com.droppa.webapi.DroppaServices.restApi;

import java.util.List;

import javax.ejb.EJB;

import com.droppa.webapi.Droppa.DAO.MySqlConnection;
import com.droppa.webapi.Droppa.DAO.UserAccountDAO;
import com.droppa.webapi.DroppaServices.UserService;
import com.droppa.webapi.DroppaServices.bean.Person;
import com.droppa.webapi.DroppaServices.bean.UserAccount;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestApi {
	
	@EJB
	UserService userService = new UserService();

	@GET
	@Path("/getuser")
	public Person getUser(@PathParam("id") String id) {
		return userService.createUserAccount(null);
	}

	@GET
	@Path("/viewallusers")
	public Response getAllUsers() {
		List<UserAccount> users = userService.getAllUsers();
		return Response.ok().entity(users).build();
	}
	
	@POST
	@Path("/mobile/confirmation/{mobile}/{code}")
	public Response confirmMobile(@PathParam("code") int code,@PathParam("mobile") String mobile) {
		String resp = userService.confirmMobile(mobile,code);
		return Response.ok(resp).build();
	}
	
	
	@POST
	@Path("/createuser")
	public Response createUser(Person person) {
		userService.createUserAccount(person);
		return Response.ok().entity(person).build();
	}
	
	@PUT
	@Path("/updateprofile")
	public UserAccount updateUserProfile(UserAccount person) {
		return userService.updateUserProfile(person);
	}
	
}
