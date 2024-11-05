package com.flightreservation.flightdao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import com.flightreservation.pojos.Reservation;

public interface ReservationDao{

	Reservation bookingDetails(int rid) throws SQLException;

	int bookFlight(int pid, int fid, int paymentid) throws SQLException;
	
	
}
