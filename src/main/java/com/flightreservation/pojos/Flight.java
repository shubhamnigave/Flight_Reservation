package com.flightreservation.pojos;

import java.sql.Date;
import java.sql.Time;

public class Flight {
	private int flight_id;
	private String flight_number;
	private String airline;
	private String departure_city;
	private String arrival_city;
	private Date departure_date;
	private Time departure_time;
	private Date arrival_date;
	private Time arrival_time;
	private Double price;
	private int availableSeats;
	
	
	public Flight(int flight_id, String flight_number, String airline, String departure_city, String arrival_city,
			Date departure_date, Time departure_time, Date arrival_date, Time arrival_time, Double price,int availableSeats) {
		super();
		this.flight_id = flight_id;
		this.flight_number = flight_number;
		this.airline = airline;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.departure_date = departure_date;
		this.departure_time = departure_time;
		this.arrival_date = arrival_date;
		this.arrival_time = arrival_time;
		this.price = price;
		this.availableSeats  = availableSeats;
	}

	
	

	public Flight(String flight_number, String airline, String departure_city, String arrival_city,
			Date departure_date) {
		super();
		this.flight_number = flight_number;
		this.airline = airline;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.departure_date = departure_date;
	}




	public Flight(int id, String airline, String departure_city, String arrival_city, Date departure_date,
			Time departure_time, Double price, int availableSeats) {
		super();
		this.flight_id = id;
		this.airline = airline;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.departure_date = departure_date;
		this.departure_time = departure_time;
		this.price = price;
		this.availableSeats = availableSeats;
	}



	@Override
	public String toString() {
		return "Flight [flight_id=" + flight_id + ", flight_number=" + flight_number + ", airline=" + airline
				+ ",\nDeparture_city=" + departure_city + ", arrival_city=" + arrival_city + "\nDeparture_date="
				+ departure_date + ", departure_time=" + departure_time + ", arrival_date=" + arrival_date
				+ ", arrival_time=" + arrival_time + ",\nPrice=" + price +  "]";
	}


	public int getFlight_id() {
		return flight_id;
	}


	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}


	public String getFlight_number() {
		return flight_number;
	}


	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public String getDeparture_city() {
		return departure_city;
	}


	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}


	public String getArrival_city() {
		return arrival_city;
	}


	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}


	public Date getDeparture_date() {
		return departure_date;
	}


	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}


	public Time getDeparture_time() {
		return departure_time;
	}


	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}


	public Date getArrival_date() {
		return arrival_date;
	}


	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}


	public Time getArrival_time() {
		return arrival_time;
	}


	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public int getAvailableSeats() {
		return availableSeats;
	}


	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}


	
	
	
	
	



	
}
