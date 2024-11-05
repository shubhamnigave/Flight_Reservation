package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import com.flightreservation.flightdao.FlightDao;
import com.flightreservation.flightdao.PassengerDao;
import com.flightreservation.flightdao.PassengerDaoImpl;
import com.flightreservation.flightdao.ReservationDao;
import com.flightreservation.flightdao.ReservationDaoImpl;
import com.flightreservation.pojos.Flight;
import com.flightreservation.pojos.Reservation;
import com.flightreservation.pojos.User;

/**
 * Servlet implementation class PassengerDetailsPage
 */
@WebServlet("/passengerdetails")
public class PassengerDetailsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	PassengerDao passengerDao;
//	ReservationDao reservationDao;
//	
//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		try {
//			
//			passengerDao = new PassengerDaoImpl();
//			reservationDao = new ReservationDaoImpl();
//			
//		}catch (Exception e) {
//			throw new ServletException("err in do-post of " + getClass(), e);
//		}
//	}
//	
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//getting the flight id and adding it to session
		String flightid = req.getParameter("fid");
		HttpSession session = req.getSession();
		try(PrintWriter pw = resp.getWriter()){
		session.setAttribute("flightId", flightid);
		FlightDao flightDao = (FlightDao)session.getAttribute("flightDao");
		Flight flightDetails = flightDao.getFlightDetails(Integer.parseInt(flightid));
		System.out.println(flightid);
//		redirect to passenger details form
			pw.print("<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <title>Passenger Details</title>\n"
					+ "    <style>\n"
					+ "        body {\n"
					+ "            font-family: Arial, sans-serif;\n"
					+ "            background: linear-gradient(135deg, #6a11cb, #2575fc);\n"
					+ "            display: flex;\n"
					+ "            justify-content: center;\n"
					+ "            align-items: center;\n"
					+ "            height: 100vh;\n"
					+ "            margin: 0;\n"
					+ "            color: #333;\n"
					+ "        }\n"
					+ "        .form-container {\n"
					+ "            background-color: #fff;\n"
					+ "            padding: 20px 40px;\n"
					+ "            border-radius: 8px;\n"
					+ "            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);\n"
					+ "            text-align: center;\n"
					+ "            max-width: 400px;\n"
					+ "            width: 100%;\n"
					+ "        }\n"
					+ "        .flight-details {\n"
					+ "            text-align: left;\n"
					+ "            margin-bottom: 20px;\n"
					+ "            font-size: 16px;\n"
					+ "            color: #555;\n"
					+ "        }\n"
					+ "        .flight-details strong {\n"
					+ "            color: #6a11cb;\n"
					+ "        }\n"
					+ "        h2 {\n"
					+ "            color: #6a11cb;\n"
					+ "            margin-bottom: 20px;\n"
					+ "        }\n"
					+ "        label {\n"
					+ "            display: block;\n"
					+ "            margin: 10px 0 5px;\n"
					+ "            font-weight: bold;\n"
					+ "            text-align: left;\n"
					+ "        }\n"
					+ "        input[type=\"text\"], input[type=\"email\"] {\n"
					+ "            width: 100%;\n"
					+ "            padding: 10px;\n"
					+ "            margin: 5px 0 20px;\n"
					+ "            border: 1px solid #ddd;\n"
					+ "            border-radius: 4px;\n"
					+ "            font-size: 16px;\n"
					+ "        }\n"
					+ "        input[type=\"submit\"] {\n"
					+ "            background-color: #6a11cb;\n"
					+ "            color: #fff;\n"
					+ "            border: none;\n"
					+ "            padding: 12px 20px;\n"
					+ "            border-radius: 4px;\n"
					+ "            cursor: pointer;\n"
					+ "            font-size: 16px;\n"
					+ "            font-weight: bold;\n"
					+ "            transition: background-color 0.3s ease;\n"
					+ "        }\n"
					+ "        input[type=\"submit\"]:hover {\n"
					+ "            background-color: #5b0faf;\n"
					+ "        }\n"
					+ "    </style>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "    <div class=\"form-container\">\n"
					+ "        <div class=\"flight-details\">\n"
					+ "            <p><strong>Flight Number:</strong> "+flightDetails.getFlight_number()+"</p>\n"
					+ "            <p><strong>Airline:</strong> "+flightDetails.getAirline()+"</p>\n"
					+ "            <p><strong>Departure City:</strong> "+flightDetails.getDeparture_city()+"</p>\n"
					+ "            <p><strong>Arrival City:</strong> "+flightDetails.getArrival_city()+"</p>\n"
					+ "            <p><strong>Departure Date:</strong> "+flightDetails.getDeparture_date()+"</p>\n"
					+ "        </div>\n"
					+ "\n"
					+ "        <h2>Passenger Details</h2>\n"
					+ "        <form action=\"gateway\" method=\"post\">\n"
					+ "            <label for=\"name\">Enter Name</label>\n"
					+ "            <input type=\"text\" id=\"name\" name=\"fn\" required />\n"
					+ "\n"
					+ "            <label for=\"email\">Enter Email</label>\n"
					+ "            <input type=\"email\" id=\"email\" name=\"em\" required />\n"
					+ "\n"
					+ "            <label for=\"phone\">Enter Phone</label>\n"
					+ "            <input type=\"text\" id=\"phone\" name=\"phn\" required />\n"
					+ "\n"
					+ "            <input type=\"submit\" value=\"Proceed to Checkout\" />\n"
					+ "        </form>\n"
					+ "    </div>\n"
					+ "</body>\n"
					+ "</html>\n");
		}catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
