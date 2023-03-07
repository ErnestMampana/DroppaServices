package com.droppa.webapi.DroppaServices.Auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.droppa.webapi.DroppaServices.bean.UserService;
import com.droppa.webapi.DroppaServices.common.MySqlConnection;


@Local
@Stateless
public class AuthenticationService {
	@EJB
	private UserService userService = new UserService();
	Connection con = MySqlConnection.getConnection();
	String extractedToken = "";

	public void authenticate(CredentialsDTO credentials) {
		try {
			String query = "select * from users where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, credentials.username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(credentials.username) && rs.getString(1).equals(credentials.password)) {	
					
					extractedToken = rs.getString(7);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String extractedToken() {
		
		return extractedToken;
	}

}
