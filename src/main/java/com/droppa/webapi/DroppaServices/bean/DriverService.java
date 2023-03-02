package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.DTO.DriverDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.core.AccountStatus;
import com.droppa.webapi.DroppaServices.pojo.Company;
import com.droppa.webapi.DroppaServices.pojo.DriverAccount;
import com.droppa.webapi.DroppaServices.pojo.Vehicle;
import com.droppa.webapi.DroppaServices.pojo.VehicleDriver;
import com.google.gson.Gson;

@Stateless
@Local
public class DriverService {
	Gson gson = new Gson();

	private VehicleDriver extractedDriver = new VehicleDriver();

	private ArrayList<DriverAccount> drivers = new ArrayList<>();

	private DriverAccount driverAccount = new DriverAccount();

	private static final Logger logger = Logger.getLogger(DriverService.class.getName());

	private Connection con = MySqlConnection.getConnection();

	@EJB
	CompanyService companyService = new CompanyService();

	@EJB
	private VehicleService vehicleService = new VehicleService();

	public VehicleDriver createDriverAccount(DriverDTO driver) {
		VehicleDriver vDriver = new VehicleDriver();
		vDriver.setId(driver.id);
		vDriver.setCelphone(driver.celphone);
		vDriver.setDriverLicence(null);
		vDriver.setName(driver.name);
		vDriver.setSurname(driver.surname);
		vDriver.setVehicles(null);

		Company company = companyService.getCompanyById(driver.companyId);	
		Vehicle vehicleData = vehicleService.getVehicleByRegistration(driver.vehicleId);
		
		String vdriver = gson.toJson(vDriver);
		String vehicle = gson.toJson(vehicleData);

		try {
			
			List<DriverAccount> drivers = getAllDrivers();
			for(int i = 0; i <= drivers.size(); i++) {
				if(drivers.get(i).getId().equals(driver.id)) 
					throw new ClientException("This driver is already registred by a different company.");
							
			}
			// save driver account
			String query = "insert into drivers(id,VehicleDriver,Vehicle,status) values(?,?,?,?)";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setString(1, driver.id);
			ps.setString(2, vdriver);
			ps.setString(3, vehicle);
			ps.setString(4, gson.toJson(AccountStatus.AWAITING_CONFIRMATION));
			ps.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vDriver;
	}

	public List<DriverAccount> getAllDrivers() {

		try {
			String data = "select * from drivers";
			PreparedStatement pt = con.prepareStatement(data);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				DriverAccount acc = new DriverAccount();
				String driver = rs.getString(2);
				String vehicle = rs.getString(3);
				acc.setId(rs.getString(1));
				acc.setDriver(gson.fromJson(driver, VehicleDriver.class));
				acc.setVehicle(gson.fromJson(vehicle, Vehicle.class));
				acc.setStatus(rs.getString(4));
				drivers.add(acc);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drivers;
	}

	public DriverAccount getDriverById(String driverId) {

		try {
			String query = "select * from drivers where Id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, driverId);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				throw new ClientException("Driver not found");

			driverAccount.setId(rs.getString(1));
			driverAccount.setDriver(gson.fromJson(rs.getString(2), VehicleDriver.class));
			driverAccount.setVehicle(gson.fromJson(rs.getString(3), Vehicle.class));
			driverAccount.setStatus(rs.getString(4));

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driverAccount;
	}

}
