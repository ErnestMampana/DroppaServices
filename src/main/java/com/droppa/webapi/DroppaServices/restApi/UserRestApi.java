package com.droppa.webapi.DroppaServices.restApi;

import java.util.List;
import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.Auth.Secured;
import com.droppa.webapi.DroppaServices.DTO.PersonDTO;
import com.droppa.webapi.DroppaServices.bean.UserService;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;

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
	@Path("/viewallusers")
	public Response getAllUsers() {
		List<UserAccount> users = userService.getAllUsers();
		return Response.ok().entity(users).build();
	}

	@PUT
	@Path("/mobile/confirmation/{mobile}/{code}")
	public Response confirmMobile(@PathParam("code") int code, @PathParam("mobile") String mobile) {
		String resp = userService.confirmMobile(mobile, code);
		return Response.ok(resp).build();
	}

	@PUT
	@Path("/email/confirmation/{email}/{code}")
	public Response confirmEmail(@PathParam("code") int code, @PathParam("email") String email) {
		String resp = userService.confirmEmail(email, code);
		return Response.ok(resp).build();
	}

	@GET
	@Path("/getuserbyid/{id}")
	public Response getUserById(@PathParam("id") String id) {
		Person person = userService.getUserById(id).getOwner();
		return Response.ok().entity(person).build();
	}

	@POST
	@Path("/createuser")
	public Response createUser(PersonDTO person) {
		userService.createUserAccount(person);
		return Response.ok().entity(person).build();
	}

	@GET
	@Path("/requestPasswordReset/{userId}")
	public Response requestPasswordReset(@PathParam("userId") String userId) {
		int otp = userService.requestPasswordReset(userId);
		return Response.ok().entity(otp).build();
	}
	
	@PUT
	@Path("/resetPassword/{username}/{otp}")
	public Response resetPassword(@PathParam("username") String username,@PathParam("otp") int otp) {
		UserAccount userAcc = userService.resetPassword(otp, username);
		return Response.ok().entity(userAcc).build();
	}

}
