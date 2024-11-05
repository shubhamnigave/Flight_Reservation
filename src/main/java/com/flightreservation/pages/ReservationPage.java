package com.flightreservation.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flightreservation.flightdao.PassengerDao;
import com.flightreservation.flightdao.PassengerDaoImpl;
import com.flightreservation.flightdao.ReservationDao;
import com.flightreservation.flightdao.ReservationDaoImpl;
import com.flightreservation.pojos.Card;
import com.flightreservation.pojos.Passenger;
import com.flightreservation.pojos.Reservation;
import com.flightreservation.pojos.User;

/**
 * Servlet implementation class ReservationPage
 */
@WebServlet("/reservationPage")
public class ReservationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PassengerDao passengerDao;
	ReservationDao reservationDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		try {
//
//			passengerDao = new PassengerDaoImpl();
//			reservationDao = new ReservationDaoImpl();
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new ServletException("err in do-post of " + getClass(), e);
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//after filling passenger details redirected to reservation page
		response.setContentType("text/html");
		System.out.println("_+_+_+_+_+_+_+_+_+_+_+");
		try (PrintWriter pw = response.getWriter()) {
			HttpSession session = request.getSession();
//			retrieving the passenger form data
			Passenger psngr = (Passenger) session.getAttribute("passenger_details");
			System.out.println("+NULL+"+psngr);
			String name = psngr.getName();
			System.out.println(name);
			String email = psngr.getEmail();
			System.out.println(email);
			String phone = psngr.getPhone();
			Passenger passenger = new Passenger(name, email, phone);
//			session.setAttribute("passenger_details", passenger);
			System.out.println(phone);
//			retrieving flightid from session
			String fd = (String) session.getAttribute("flightId");
			System.out.println(fd);
			int fid = Integer.parseInt(fd);
			System.out.println(fid);
//			session.setAttribute("fid", fid);
//			pw.print("<h1>"+fid+"</h1>");
			Card card = (Card)session.getAttribute("card_details");
			System.out.println("Card details"+card);
			int paymentid = ((PassengerDao)session.getAttribute("passengerDao")).makePayment(card);
			System.out.println("pid:"+paymentid);
//			adding passenger to passengers table
			int id = ((PassengerDao)session.getAttribute("passengerDao")).addPassenger(name, email, phone);
			//retrieving the user details from session
			User user = (User) session.getAttribute("user_info");
			int uid = user.getId();
			System.out.println("psid: "+id+" userid: "+uid);
			if(paymentid==0) {
				pw.print("<h3>Payment failed <a href='search.html'>Retry</a></h3>");
			}
//			making reservation storing in reservation table;
			//get reservation/booking id
			int rid = ((ReservationDao)session.getAttribute("reservationDao")).bookFlight(id, fid,paymentid);
			session.setAttribute("rid", rid);
			System.out.println("rid"+rid);
			Reservation reserve = ((ReservationDao)session.getAttribute("reservationDao")).bookingDetails(rid);
			session.invalidate();
			//get the booking details to show customer
			if (reserve == null)
				pw.print("<h2>Booking failed</h2>");
			else
				pw.print("<!DOCTYPE html>\n" + 
				         "<html lang=\"en\">\n" + 
				         "<head>\n" + 
				         "    <meta charset=\"UTF-8\">\n" + 
				         "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
				         "    <title>Flight Ticket</title>\n" + 
				         "</head>\n" +
				         "<body style=\"background: linear-gradient(to right, #0073e6, #8e2de2); font-family: Arial, sans-serif; display: flex; flex-direction:row; justify-content: center; align-items: center; height: 100vh; margin: 0;\">\n" + 
				         "    <div class=\"ticket\" style=\"border-radius: 15px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3); width: 600px; overflow: hidden; background-color: #fff;\">\n" + 
				         "        <div class=\"ticket-header\" style=\"background-color: #0073e6; color: white; padding: 20px; display: flex; justify-content: space-between; align-items: center;\">\n" + 
				         "            <div class=\"airline-info\" style=\"display: flex; align-items: center;\">\n" +
				         "                <span class=\"airline-name\" style='font-size:20px; font-weight: bold;'>"+reserve.getAirline()+"</span>\n" + 
				         "            </div>\n" + 
				         "            <div class=\"ticket-type\" style=\"font-weight: bold; font-size: 24px;\">BOARDING PASS</div>\n" + 
				         "        </div>\n" + 
				         "        <div class=\"ticket-body\" style=\"padding: 20px; display: flex; background-color: #f3f4f6;\">\n" + 
				         "            <div class=\"left-section\" style=\"width: 50%;\">\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Name of Passenger:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getName()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Flight:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getFlightNo()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Departure:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getFrom()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Arrival:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getTo()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"display: flex; flex-direction: column; align-items: center; padding: 10px; border: 1px solid #0073e6; border-radius: 8px;\">\n" + 
				         "                    <span style=\"font-weight: bold; font-size: 18px; color: #0073e6;\">Gate</span>\n" + 
				         "                    <span style=\"font-size: 22px; font-weight: bold;\">K18</span>\n" + 
				         "                </div>\n" + 
				         "            </div>\n" + 
				         "            <div class=\"right-section\" style=\"width: 50%;\">\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Boarding Time:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">08:30 AM</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Departure Date:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getDepartureDate()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Departure Time:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getDepartureTime()+"</span>\n" + 
				         "                </div>\n" + 
				         "                <div class=\"section\" style=\"margin-bottom: 15px; font-size: 16px;\">\n" + 
				         "                    <span class=\"label\" style=\"font-weight: bold;\">Seat:</span>\n" + 
				         "                    <span class=\"info\" style=\"margin-left: 5px;\">"+reserve.getSeatNo()+"</span>\n" + 
				         "                </div>\n" + 
				         "            </div>\n" + 
				         "        </div>\n" + 
				         "        <div class=\"ticket-footer\" style=\"background-color: #0073e6; text-align: center; padding: 15px; color: white;\">\n" + 
				         "            <div class=\"barcode\" style=\"height: 40px; margin-top: 10px;\">\n" + 
				         "                <div class=\"barcode-line\" style=\"display: inline-block; width: 4px; height: 100%; background-color: white; margin-right: 2px;\"></div>\n" + 
				         "                <div class=\"barcode-line\" style=\"display: inline-block; width: 4px; height: 100%; background-color: white; margin-right: 2px;\"></div>\n" + 
				         "                <div class=\"barcode-line\" style=\"display: inline-block; width: 4px; height: 100%; background-color: white; margin-right: 2px;\"></div>\n" + 
				         "                <div class=\"barcode-line\" style=\"display: inline-block; width: 4px; height: 100%; background-color: white; margin-right: 2px;\"></div>\n" + 
				         "                <div class=\"barcode-line\" style=\"display: inline-block; width: 4px; height: 100%; background-color: white; margin-right: 2px;\"></div>\n" + 
				         "            </div>\n" + 
				         "        </div>\n" + 
				         "    </div>\n" + 
				         "<a href=\"register.html\" style=\"\n"
				         + "    display: inline-block;\n"
				         + "    padding: 10px 20px;\n"
				         + "    margin-top: 20px;\n"
				         + "    background-color: #0073e6;\n"
				         + "    color: white;\n"
				         + "    text-decoration: none;\n"
				         + "    font-weight: bold;\n"
				         + "    border-radius: 8px;\n"
				         + "    text-align: center;\n"
				         + "    transition: background-color 0.3s;\n"
				         + "    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);\n"
				         + "\">\n"
				         + "   Exit \n"
				         + "</a>\n"+
				         "</body>\n" + 
				         "</html>\n");

//			response.sendRedirect("reservationPage");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}
}
