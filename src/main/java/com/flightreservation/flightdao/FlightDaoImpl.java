package com.flightreservation.flightdao;

import static com.flightreservation.utils.DBUtils.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.flightreservation.pojos.Flight;
public class FlightDaoImpl implements FlightDao {
	private Connection cn;
	private PreparedStatement ps,ps2;
	public FlightDaoImpl() throws SQLException {
		// TODO Auto-generated constructor stub
		cn = getConnection();
		ps = cn.prepareStatement("select * from flights where departure_city=? and arrival_city=? and departure_date=? order by price");
		ps2 = cn.prepareCall("select * from flights where flight_id=?");
	}
	//select * from flights where departure_city='New York' and arrival_city='Los Angeles' and dept_time='2024-11-01' order by price
	@Override
	public List<Flight> searchFlights(String departureCity,String arrivalCity,String deptDate) throws SQLException{
		System.out.println("--------------------------");
		ps.setString(1, departureCity);
		ps.setString(2, arrivalCity);
		ps.setDate(3, Date.valueOf(deptDate));
		List<Flight> flights = new ArrayList<>();
		try(ResultSet rs = ps.executeQuery()){
			while(rs.next()) {
				//int flight_id, String flight_number, String airline, String departure_city, String arrival_city,
				//Date departure_date, Time departure_time, Date arrival_date, Time arrival_time, Double price
				//String airline, String departure_city, String arrival_city, Date departure_date,
				//Time departure_time, Double price, int availableSeats
				flights.add(new Flight(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getTime(7),rs.getDate(8),rs.getTime(9), rs.getDouble(10), rs.getInt(11)));			
			}
		}
		System.out.println(flights);
		return flights;
	}
	
	@Override
	public Flight getFlightDetails(int flightId) throws SQLException{
		ps2.setInt(1, flightId);
		try(ResultSet rs = ps2.executeQuery()){
			if(rs.next()) {
				return new Flight(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
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
