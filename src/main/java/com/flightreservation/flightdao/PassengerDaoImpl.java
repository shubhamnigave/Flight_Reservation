package com.flightreservation.flightdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.flightreservation.pojos.Card;

import static com.flightreservation.utils.DBUtils.*;

public class PassengerDaoImpl implements PassengerDao{
	private Connection cn;
	private PreparedStatement ps,ps2,ps3,ps4;
	
	public PassengerDaoImpl() throws SQLException {
		// TODO Auto-generated constructor stub
		cn = getConnection();
		ps = cn.prepareStatement("Insert into passengers values(default,?,?,?)");
		ps2 = cn.prepareStatement("Select id from passengers where email=? and phone=?");
		ps3 = cn.prepareStatement("insert into cards values(default,?,?,?,?,?)");
		ps4 = cn.prepareStatement("select payment_id from cards where card_no=? and payment_date_time>=?");
	}
	
	@Override 
	public int getPassengerId(String email, String phone) throws SQLException{
		ps2.setString(1, email);
		ps2.setString(2, phone);
		try(ResultSet rs = ps2.executeQuery()){
			if(rs.next())
				return rs.getInt(1);
		}
		return 0;
	}
	
	@Override
	public int makePayment(Card card) throws SQLException{
		ps3.setString(1, card.getCardno());
		ps3.setString(2,card.getName());
		ps3.setDate(3, card.getExpiry());
		ps3.setInt(4, card.getCvv());
		ps3.setTimestamp(5, card.getPaymentTime());
		int rows = ps3.executeUpdate();
		if(rows==1)
			return getPaymentId(card.getCardno(),Timestamp.valueOf(LocalDateTime.now().minusMinutes(20)));
		return 0;
	}
	
	private int getPaymentId(String cardno, Timestamp paymentTime) throws SQLException {
		// TODO Auto-generated method stub
		ps4.setString(1, cardno);
		ps4.setTimestamp(2, paymentTime);
		try(ResultSet rs = ps4.executeQuery()){
			if(rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public int addPassenger(String name, String email, String phone) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, phone);
		int rows = ps.executeUpdate();
		if(rows==1)
			return getPassengerId(email, phone);
		return 0;
	}

}
