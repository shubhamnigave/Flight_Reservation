package com.flightreservation.pojos;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Card {
	private String cardno;
	private String name; 
	private Date expiry; 
	private int cvv;
	private Timestamp paymentTime;
	
	public Card(String cardno, String name, Date expiry, int cvv) {
		super();
		this.cardno = cardno;
		this.name = name;
		this.expiry = expiry;
		this.cvv = cvv;
		this.paymentTime = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public Timestamp getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Override
	public String toString() {
		return "Card [cardno=" + cardno + ", name=" + name + ", expiry=" + expiry + ", cvv=" + cvv + "]";
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
}
