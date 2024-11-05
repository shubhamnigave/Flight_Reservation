package com.flightreservation.flightdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flightreservation.pojos.Reservation;

import static com.flightreservation.utils.DBUtils.*;

public class ReservationDaoImpl implements ReservationDao {
	private Connection cn;
	private PreparedStatement ps,ps2,ps3,ps4;
	public ReservationDaoImpl() throws SQLException{
		// TODO Auto-generated constructor stub
		cn = getConnection();
		ps = cn.prepareStatement("Insert into reservation values(default,?,?,?)");
		ps2 = cn.prepareStatement("Select booking_id from reservation where passenger_id=? and flight_id=?");
		ps3 = cn.prepareStatement("select booking_id ,p.passenger_name, f.flight_number,f.departure_city,f.arrival_city,f.departure_date,f.departure_time,f.available_seats,f.airline from reservation r inner join passengers p on r.passenger_id=p.id inner join flights f on r.flight_id=f.flight_id where r.booking_id=?");
		ps4 = cn.prepareStatement("Update flights set available_seats=available_seats-1 where flight_id=?");
	}
	
	public int getBookingId(int pid,int fid) throws SQLException {
		ps2.setInt(1, pid);
		ps2.setInt(2,fid);
		try(ResultSet rs = ps2.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}
	
	
	
	@Override
	public int bookFlight(int pid,int fid,int paymentid) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("fid"+fid);
		ps.setInt(1, pid);
		ps.setInt(2, fid);
//		System.out.println("paymentid"+paymentid);
		ps.setInt(3, paymentid);
		int rows = ps.executeUpdate();
		ps4.setInt(1, fid);
		int r = ps4.executeUpdate();
		System.out.println("seats"+r);
		if(rows == 1)
			return getBookingId(pid, fid);
		return 0;
	}

	@Override
	public Reservation bookingDetails(int rid) throws SQLException{
		ps3.setInt(1, rid);
		try(ResultSet rs = ps3.executeQuery()){
			if(rs.next()) {
				return new Reservation(rs.getString(9),rid, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getTime(7), rs.getInt(8));
			}
		}
		return null;
	}
}
