package com.droppa.webapi.DroppaServices.restApi;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.Auth.CredentialsDTO;
import com.droppa.webapi.DroppaServices.bean.LoginService;
import com.droppa.webapi.DroppaServices.pojo.UserAccount;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRestApi {
	
	@EJB private LoginService loginService = new LoginService();
	
	@POST
	@Path("/userlogin")
	public Response userLogin(CredentialsDTO credentialsDto) {
		UserAccount userAcc  = loginService.userLogin(credentialsDto.username, credentialsDto.password);
		return Response.ok().entity(userAcc).build();
	}

}
