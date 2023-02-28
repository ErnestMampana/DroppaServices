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

import com.droppa.webapi.DroppaServices.DTO.CompanyDTO;
import com.droppa.webapi.DroppaServices.common.ClientException;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;
import com.droppa.webapi.DroppaServices.pojo.Company;
import com.droppa.webapi.DroppaServices.pojo.DriverAccount;
import com.droppa.webapi.DroppaServices.pojo.Person;
import com.droppa.webapi.DroppaServices.pojo.Vehicle;
import com.droppa.webapi.DroppaServices.pojo.VehicleDriver;
import com.google.gson.Gson;

@Stateless
@Local
public class CompanyService {

	Gson gson = new Gson();

	private Connection con = MySqlConnection.getConnection();
	private Company extractedCompany = new Company();
	private ArrayList<Company> companies = new ArrayList<>();
	private static final Logger logger = Logger.getLogger(CompanyService.class.getName());
	@EJB
	UserService userService = new UserService();
	
	public Company createCompany(CompanyDTO companyDTO) {
		Person person = userService.getUserById(companyDTO.ownerId);
		Company company = new Company();
		company.setCompanyName(companyDTO.companyName);
		company.setLocation(companyDTO.location);
		company.setId(companyDTO.ownerId);
		company.setOwner(person);
		company.setDrivers(null);
		company.setListOfVehicles(null);
		
		String owner = gson.toJson(person);
		String drivers = gson.toJson(company.getDrivers());
		String vehicles = gson.toJson(company.getListOfVehicles());

		try {
			String check = "select * from companies";
			PreparedStatement psc = this.con.prepareStatement(check);
			ResultSet rs = psc.executeQuery();

			while (rs.next()) {
				String pers = rs.getString(3);
				extractedCompany = gson.fromJson(pers, Company.class);
				if (rs.getString(1).equals(company.getOwner().getId())) {
					throw new ClientException(company.getOwner().getUserName()+" has a registered company with us already");
				}

			}
			// save company details
			String query = "insert into companies(id,companyName,companyOwner,companyDrivers,companyVehicles,location) values(?,?,?,?,?,?)";
			PreparedStatement ps = this.con.prepareStatement(query);
			ps.setString(1, company.getOwner().getId());
			ps.setString(2, company.getCompanyName());
			ps.setString(3, owner);
			ps.setString(4, drivers);
			ps.setString(5, vehicles);
			ps.setString(6, company.getLocation());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public Company getCompanyById(String id) {
		try {
			String query = "select * from companies where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String pers = rs.getString(3); 
				String drivers = rs.getString(4);
				extractedCompany.setId(rs.getString(1));
				extractedCompany.setCompanyName(rs.getString(2));
				extractedCompany.setOwner(gson.fromJson(pers, Person.class));
				extractedCompany.setDrivers(null);
				extractedCompany.setListOfVehicles(null);
				extractedCompany.setLocation(rs.getString(6));
			}
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return extractedCompany;
	}

	public List<Company> viewAllCompanies() {
		try {
			String data = "select * from companies";
			PreparedStatement pt = con.prepareStatement(data);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				String owner = rs.getString(3);
				String driver = rs.getString(4);
				String vehicle = rs.getString(5);
				company.setId(rs.getString(1));
				company.setOwner(gson.fromJson(owner, Person.class));
				company.setCompanyName(rs.getString(2));
				company.setListOfVehicles(null);
				company.setDrivers(null);
				company.setLocation(rs.getString(6));
				companies.add(company);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companies;
	}
	
}
