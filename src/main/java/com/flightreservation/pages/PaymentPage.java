package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flightreservation.pojos.Card;
import com.flightreservation.pojos.Passenger;

/**
 * Servlet implementation class PaymentPage
 */
@WebServlet("/payment")
public class PaymentPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	
	/**
	 * @see HttpServlet#doPost(HttpServl
	 * etRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			String cardno = request.getParameter("card-number");
			String name = request.getParameter("name");
			String expiry = request.getParameter("exp-date");
			String cvv = request.getParameter("cvv");
			Card card = new Card(cardno, name, Date.valueOf(expiry), Integer.parseInt(cvv));
			session.setAttribute("card_details", card);
			System.out.println("-=-=-=-=-=-=-=-=-");
			response.sendRedirect("reservationPage");
	}


//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		resp.sendRedirect("payment.html");
//	}

}
