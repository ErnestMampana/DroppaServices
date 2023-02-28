/**
 * 
 */
package com.droppa.webapi.DroppaServices.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.droppa.webapi.DroppaServices.restApi.AdminRestApi;

import jakarta.ws.rs.core.Response;

/**
 * @author Ernest Mampana
 *
 */
class AdminServiceTest {
	
	private AdminRestApi adminRest = new AdminRestApi();

	/**
	 * Test method for {@link com.droppa.webapi.DroppaServices.restApi.AdminRestApi#suspendDriver(java.lang.String)}.
	 */
	@Test
	void testSuspendDriver() {
		String driverId = "th847991thlamjn";
		Response resp = adminRest.suspendDriver(driverId);
		System.out.println(resp.getEntity());
	}

	/**
	 * Test method for {@link com.droppa.webapi.DroppaServices.restApi.AdminRestApi#activateDriver(java.lang.String)}.
	 */
	@Test
	void testActivateDriver() {
		String driverId = "th847991thlamjn";
		Response resp = adminRest.activateDriver(driverId);
		System.out.println(resp.getEntity());
	}

}
