///**
// * 
// */
//package com.droppa.webapi.DroppaServices.test;
//
////import static org.junit.jupiter.api.Assertions.*;
//
////import org.junit.jupiter.api.Test;
//
//import com.droppa.webapi.DroppaServices.DTO.DriverDTO;
//import com.droppa.webapi.DroppaServices.pojo.DriverAccount;
//import com.droppa.webapi.DroppaServices.pojo.UserAccount;
//import com.droppa.webapi.DroppaServices.restApi.DriverRestApi;
//
//import jakarta.ws.rs.core.Response;
//import org.junit.Test;
//
///**
// * @author Ernest Mampana
// *
// */
//class TestDriverService {
//	
//	DriverRestApi driverRest = new DriverRestApi();
//
//	/**
//	 * Test method for {@link com.droppa.webapi.DroppaServices.bean.DriverService#createDriverAccount(com.droppa.webapi.DroppaServices.DTO.DriverDTO)}.
//	 */
//	//@Test
//	void testCreateDriverAccount() {
//		DriverDTO driverDto = new DriverDTO();
//		driverDto.id = "fdfdfdfdfdf";
//		driverDto.name = "Jabulile";
//		driverDto.surname = "Khoza";
//		//driverDto.celphone = 0785542569;
//	}
//
//	/**
//	 * Test method for {@link com.droppa.webapi.DroppaServices.bean.DriverService#getAllDrivers()}.
//	 */
//	//@Test
////	void testGetAllDrivers() {
////		fail("Not yet implemented");
////	}
//	
//	@Test
//	void testGetDriverById() {
//		String driverId = "th847991thlamjn";
//		Response resp = driverRest.getDriverById(driverId);
//		DriverAccount driverAcc = (DriverAccount)resp.getEntity();
//		System.out.println(driverAcc.getDriver().getSurname());
//	}
//
//}
