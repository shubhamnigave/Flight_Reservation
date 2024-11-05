package com.flightreservation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;
	public static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flights","root","shubham");
		
	}
	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if(connection!=null)
			connection.close();
	}
}
