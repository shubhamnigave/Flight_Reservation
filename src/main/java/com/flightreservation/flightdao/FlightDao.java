package com.flightreservation.flightdao;

import java.sql.SQLException;
import java.util.List;

import com.flightreservation.pojos.Flight;

public interface FlightDao {

	List<Flight> searchFlights(String departureCity, String arrivalCity, String deptDate) throws SQLException;

	void cleanUp() throws SQLException;

	Flight getFlightDetails(int flightId) throws SQLException;
}
