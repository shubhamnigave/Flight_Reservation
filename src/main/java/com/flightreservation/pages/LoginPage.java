package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import com.flightreservation.flightdao.FlightDao;
import com.flightreservation.flightdao.FlightDaoImpl;
import com.flightreservation.flightdao.PassengerDao;
import com.flightreservation.flightdao.PassengerDaoImpl;
import com.flightreservation.flightdao.ReservationDao;
import com.flightreservation.flightdao.ReservationDaoImpl;
import com.flightreservation.flightdao.UserDao;
import com.flightreservation.flightdao.UserDaoImpl;
import com.flightreservation.pojos.User;
import static com.flightreservation.utils.DBUtils.*;
/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/login")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
	FlightDao flightDao;
	PassengerDao passengerDao;
	ReservationDao reservationDao;
	User user;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		try {
			openConnection();
			userDao = new UserDaoImpl();
			flightDao = new FlightDaoImpl();
			passengerDao = new PassengerDaoImpl();
			reservationDao = new ReservationDaoImpl();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
		
	}
	
	
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		resp.setContentType("text/html");
//		
//	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		
		try(PrintWriter pw = resp.getWriter()){
			String email = req.getParameter("em");
			String password = req.getParameter("pass");
			user = userDao.login(email, password);
			if(user==null) {
				pw.print("<h4>Invalid email or password. <a href='login.html'>Retry</a></h4>");
		
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("user_info", user);
				if(flightDao==null) System.out.println("null");
				session.setAttribute("flightDao", flightDao);
				session.setAttribute("userDao", userDao);
				session.setAttribute("passengerDao", passengerDao);
				session.setAttribute("reservationDao", reservationDao);
//				resp.sendRedirect("search.html");
				pw.print("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\" />\r\n"
						+ "    <title>Search Flight</title>\r\n"
						+ "    <style>\r\n"
						+ "        /* Background gradient */\r\n"
						+ "        body {\r\n"
						+ "            display: flex;\r\n"
						+ "            justify-content: center;\r\n"
						+ "            align-items: center;\r\n"
						+ "            height: 100vh;\r\n"
						+ "            margin: 0;\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background: linear-gradient(135deg, #6a11cb, #2575fc);\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Form container styling */\r\n"
						+ "        .search-container {\r\n"
						+ "            background-color: #ffffff;\r\n"
						+ "            padding: 30px 40px;\r\n"
						+ "            border-radius: 12px;\r\n"
						+ "            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);\r\n"
						+ "            width: 320px;\r\n"
						+ "            text-align: center;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Form title */\r\n"
						+ "        .search-container h1 {\r\n"
						+ "            font-size: 1.5rem;\r\n"
						+ "            color: #6a11cb;\r\n"
						+ "            margin-bottom: 10px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Greeting text */\r\n"
						+ "        .search-container h2 {\r\n"
						+ "            font-size: 1rem;\r\n"
						+ "            color: #333;\r\n"
						+ "            margin: 5px 0;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Label styling */\r\n"
						+ "        label {\r\n"
						+ "            display: block;\r\n"
						+ "            font-size: 0.9rem;\r\n"
						+ "            color: #333;\r\n"
						+ "            margin-bottom: 5px;\r\n"
						+ "            text-align: left;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Input fields styling */\r\n"
						+ "        input[type=\"text\"],\r\n"
						+ "        input[type=\"date\"] {\r\n"
						+ "            width: 100%;\r\n"
						+ "            padding: 10px;\r\n"
						+ "            margin-bottom: 15px;\r\n"
						+ "            border: 1px solid #ddd;\r\n"
						+ "            border-radius: 6px;\r\n"
						+ "            font-size: 1rem;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Submit button styling */\r\n"
						+ "        input[type=\"submit\"] {\r\n"
						+ "            width: 100%;\r\n"
						+ "            padding: 10px;\r\n"
						+ "            color: #fff;\r\n"
						+ "            font-size: 1rem;\r\n"
						+ "            font-weight: bold;\r\n"
						+ "            background-color: #6a11cb;\r\n"
						+ "            border: none;\r\n"
						+ "            border-radius: 5px;\r\n"
						+ "            cursor: pointer;\r\n"
						+ "            transition: background-color 0.3s ease;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        /* Hover effect for submit button */\r\n"
						+ "        input[type=\"submit\"]:hover {\r\n"
						+ "            background-color: #5b0faf;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <div class=\"search-container\">\r\n"
						+ "        <h1>Flight Search Form</h1>\r\n"
						+ "       <h2>Hello, "+user.getFirstName()+" "+user.getLastName()+"</h2>\r\n"
						+ "\r\n"
						+ "        <form action=\"search\" method=\"post\">\r\n"
						+ "            <label for=\"from\">From</label>\r\n"
						+ "            <input type=\"text\" id=\"from\" name=\"from\" required />\r\n"
						+ "\r\n"
						+ "            <label for=\"to\">To</label>\r\n"
						+ "            <input type=\"text\" id=\"to\" name=\"to\" required />\r\n"
						+ "\r\n"
						+ "            <label for=\"ddate\">Departure Date</label>\r\n"
						+ "            <input type=\"date\" id=\"ddate\" name=\"ddate\" required />\r\n"
						+ "\r\n"
						+ "            <input type=\"submit\" value=\"Search\" />\r\n"
						+ "        </form>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}
	

	


}
