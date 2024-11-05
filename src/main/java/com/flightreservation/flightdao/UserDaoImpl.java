package com.flightreservation.flightdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flightreservation.pojos.User;

import static com.flightreservation.utils.DBUtils.*;
public class UserDaoImpl implements UserDao {
	private Connection connection;
	private PreparedStatement ps,ps2;
	
	public UserDaoImpl() throws SQLException{
		// TODO Auto-generated constructor stub
		connection = getConnection();
		ps = connection.prepareStatement("Insert into users values(default,?,?,?,?,?)");
		ps2 = connection.prepareStatement("Select * from users where email=? and password=?");
	}
	
	@Override
	public String register(String firstName, String lastName, String email, String password, String dob) throws SQLException {
		// TODO Auto-generated method stub
		Date db = Date.valueOf(dob);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, email);
		ps.setString(4, password);
		ps.setDate(5, db);
		int rows = ps.executeUpdate();
		if(rows==1)
			return "success";
		return null;
	}
	
	
	
	@Override
	public User login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		ps2.setString(1, email);
		ps2.setString(2, password);
		try(ResultSet rs = ps2.executeQuery()){
			if(rs.next()) {
				return new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
			}
		}
		return null;
	}
	@Override
	public void cleanUp() throws SQLException {
		if(ps!=null)
			ps.close();
	}
}
