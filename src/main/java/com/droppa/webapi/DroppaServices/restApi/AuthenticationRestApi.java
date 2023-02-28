package com.droppa.webapi.DroppaServices.restApi;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.Auth.AuthenticationService;
import com.droppa.webapi.DroppaServices.Auth.CredentialsDTO;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationRestApi {
	
	@EJB
	private AuthenticationService service = new AuthenticationService();
	
	@POST
	@Path("/authenticate")
	public Response authenticate(CredentialsDTO credentials) {
		service.authenticate(credentials);
		return Response.ok().build();
	}

}
