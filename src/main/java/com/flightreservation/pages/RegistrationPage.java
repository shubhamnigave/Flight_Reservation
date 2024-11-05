package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flightreservation.flightdao.UserDao;
import com.flightreservation.flightdao.UserDaoImpl;
import static com.flightreservation.utils.DBUtils.*;
/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/register")
public class RegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserDao users;
   
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			openConnection();
			users = new UserDaoImpl();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy(){
		// TODO Auto-generated method stub
		try {
			users.cleanUp();
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("err in do-post of " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String fname = request.getParameter("fn");
			String lname = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			String dob = request.getParameter("dob");
			String res = users.register(fname, lname, email, password, dob);
			if(res.equals("success")) {
			    pw.print("<div style='text-align: center; margin-top: 20px; font-family: Arial, sans-serif;'>"
			           + "    <h3 style='color: #0073e6; font-size: 24px;'>User registered successfully!</h3>"
			           + "    <h4 style='font-size: 20px; margin-top: 10px;'>"
			           + "        <a href='login.html' style='color: white; background-color: #0073e6; text-decoration: none; padding: 8px 16px; border-radius: 5px; transition: background-color 0.3s; display: inline-block;'>"
			           + "            Sign In"
			           + "        </a>"
			           + "    </h4>"
			           + "</div>");
			}

		}catch (Exception e) {
			// TODO: handle exception
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}

}
