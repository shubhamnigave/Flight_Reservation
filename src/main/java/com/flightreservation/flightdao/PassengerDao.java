package com.flightreservation.flightdao;

import java.sql.SQLException;

import com.flightreservation.pojos.Card;

public interface PassengerDao {
	int addPassenger(String name,String email, String phone) throws SQLException;

	int getPassengerId(String email, String phone) throws SQLException;

	int makePayment(Card card) throws SQLException;
}
