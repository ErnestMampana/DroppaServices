/**
 * 
 */
package com.droppa.webapi.DroppaServices.Auth;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.bean.AdminService;
import com.droppa.webapi.DroppaServices.common.ClientException;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 * @author Ernest Mampana
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@EJB
	AdminService adminService = new AdminService();

	private static final Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Validate the Authorization header
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			ClientException ex = new ClientException("Authentication required",
					Response.Status.BAD_REQUEST.getStatusCode());
			logger.info("Authentication not provided");
			throw ex;
		}

		// Extract the token from the Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();
		if (token.contains(":")) {

			try {
				validateToken(token);
			} catch (Exception e) {
				logger.info("Aborting, token validation failed");
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}

//			String username = provider.getSubject(token);
//			loadRoles(username);
//			setSecurityContext(context, username);
		}
	}

	private void validateToken(String key) throws Exception {
		boolean valid = adminService.validateToken(key);
		if (!valid)
			throw new ClientException("Authentication failure");
	}
}
