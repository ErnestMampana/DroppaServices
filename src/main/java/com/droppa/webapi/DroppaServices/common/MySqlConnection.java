package com.droppa.webapi.DroppaServices.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/droppa","root","12345");
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return con;
	}

}
