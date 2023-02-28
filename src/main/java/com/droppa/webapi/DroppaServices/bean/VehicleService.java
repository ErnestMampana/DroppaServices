package com.droppa.webapi.DroppaServices.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.DTO.VehicleDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.pojo.Company;
import com.droppa.webapi.DroppaServices.pojo.Vehicle;
import com.google.gson.Gson;

@Stateless
@Local
public class VehicleService {
	Connection con = MySqlConnection.getConnection();
	Gson gson = new Gson();
	ArrayList<Vehicle> vehicles = new ArrayList<>();
	@EJB
	CompanyService companyService = new CompanyService();

	public Vehicle registerVehicle(VehicleDTO vehicleDto) {

		Company company = companyService.getCompanyById(vehicleDto.companyId);
		Vehicle vehicle = new Vehicle(vehicleDto.registration, vehicleDto.make, vehicleDto.type,
				vehicleDto.discExpiryDate, null, company);

		String gsonCompany = gson.toJson(vehicle.getCompany());
		String drivers = gson.toJson(vehicle.getDrivers());
		Company extractedCompany = new Company();

		try {
			String check = "select * from vehicles";
			PreparedStatement psc = con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(6);
				extractedCompany = gson.fromJson(pers, Company.class);
				if (rs.getString(1).equals(vehicle.getRegistration())) {
					throw new ClientException("This vehicle has been registered by "
							+ vehicle.getCompany().getCompanyName() + " already");
				}
			}
			// save vehicle data
			String query = "insert into vehicles(Registration,Make,VehicleType,DiscExpiryDate,Drivers,Company) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, vehicle.getRegistration());
			ps.setString(2, vehicle.getMake());
			ps.setString(3, vehicle.getType());
			ps.setString(4, vehicle.getDiscExpiryDate());
			ps.setString(5, drivers);
			ps.setString(6, gsonCompany);
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	public List<Vehicle> getAllVehicles() {
		try {
			String query = "select * from vehicles";
			PreparedStatement pt = con.prepareStatement(query);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				Vehicle vs = new Vehicle();
				String pers = rs.getString(6);
				String drivers = rs.getString(5);
				vs.setRegistration(rs.getString(1));
				vs.setMake(rs.getString(2));
				vs.setType(rs.getString(3));
				vs.setDiscExpiryDate(rs.getString(4));
				vs.setDrivers(null);
				vs.setCompany(gson.fromJson(pers, Company.class));
				vehicles.add(vs);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicles;
	}
}
