package com.flightreservation.flightdao;

import java.sql.Date;
import java.sql.SQLException;

import com.flightreservation.pojos.User;

public interface UserDao{
	String register(String firstName, String lastName, String email,String password,String dob) throws SQLException;
	User login(String email, String password) throws SQLException;
	void cleanUp() throws SQLException;
}
