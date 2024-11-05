package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flightreservation.flightdao.FlightDao;
import com.flightreservation.flightdao.FlightDaoImpl;
import com.flightreservation.flightdao.UserDao;
import com.flightreservation.pojos.Flight;
import com.flightreservation.pojos.User;

import static com.flightreservation.utils.DBUtils.*;

/**
 * Servlet implementation class SearchFlightPage
 */
@WebServlet("/search")
public class SearchFlightPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			HttpSession session = request.getSession();
			FlightDao flightDao = (FlightDao) session.getAttribute("flightDao");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String departure = request.getParameter("ddate");
			if(flightDao!=null) {

				List<Flight> flightsList = new ArrayList<>(flightDao.searchFlights(from, to, departure));
				pw.print("<style>");
				pw.print("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #6a11cb, #2575fc); color: #333; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
				pw.print("table { width: 80%; max-width: 1000px; margin: 20px auto; border-collapse: collapse; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
				pw.print("th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }");
				pw.print("th { background-color: #6a11cb; color: #fff; text-align: center; font-weight: bold; }");
				pw.print("tr:nth-child(even) { background-color: #f3f3f3; }");
				pw.print("tr:hover { background-color: #e9f0ff; }");
				pw.print("h2 { text-align: center; color: #fff; font-size: 1.5rem; }");
				pw.print("form { display: inline; }");
				pw.print("input[type='submit'] { background-color: #6a11cb; color: #fff; border: none; padding: 8px 12px; border-radius: 4px; cursor: pointer; transition: background-color 0.3s ease; font-weight: bold; }");
				pw.print("input[type='submit']:hover { background-color: #5b0faf; }");
				pw.print("</style>");

				if (flightsList == null || flightsList.isEmpty()) {
				    pw.print("<h2>No flight available!!</h2>");
				} else {
				    pw.print("<table border='0'>\n"
				            + "    <tr>\n"
				            + "        <th>Airline</th>\n"
				            + "        <th>Departure City</th>\n"
				            + "        <th>Arrival City</th>\n"
				            + "        <th>Departure Date</th>\n"
				            + "        <th>Departure Time</th>\n"
				            + "        <th>Price</th>\n"
				            + "        <th>Available Seats</th>\n"
				            + "        <th>Select</th>\n"
				            + "    </tr>\n");
				    
				    for (Flight f : flightsList) {
				        pw.print("    <tr>\n"
				                + "        <td>" + f.getAirline() + "</td>\n"
				                + "        <td>" + f.getDeparture_city() + "</td>\n"
				                + "        <td>" + f.getArrival_city() + "</td>\n"
				                + "        <td>" + f.getDeparture_date() + "</td>\n"
				                + "        <td>" + f.getDeparture_time() + "</td>\n"
				                + "        <td>$" + f.getPrice() + "</td>\n"
				                + "        <td>" + f.getAvailableSeats() + "</td>\n"
				                + "        <td><form method='post' action='passengerdetails'>"
				                + "            <input type='text' hidden name='fid' value='" + f.getFlight_id() + "'/>"
				                + "            <input type='submit' value='Select'></form></td>\n"
				                + "    </tr>");
				    }
				    pw.print("</table>");
				}


			}else {
				pw.print("<h2>Error</h2>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		try {			
//			fd.cleanUp();
//			closeConnection();
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new RuntimeException("err in do-post of " + getClass(), e);
//		}
//	}

}
