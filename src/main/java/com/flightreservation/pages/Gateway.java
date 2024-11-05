package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flightreservation.pojos.Passenger;

/**
 * Servlet implementation class Gateway
 */
@WebServlet("/gateway")
public class Gateway extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		try(PrintWriter pw = response.getWriter()) {
			String name = request.getParameter("fn");
			String email = request.getParameter("em");
			String phone = request.getParameter("phn");
			Passenger psngr  = new Passenger(name, email, phone);
			System.out.println("Passenger"+psngr);
			session.setAttribute("passenger_details", psngr);
			response.sendRedirect("payment.html");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

}
