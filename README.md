### Flight Reservation System - Overview

**Project Name**: Flight-Reservation-System2  
**Technologies**: JAX-WS Web Services, Java

This project is a **Flight Reservation System** that allows users to search for flights, make bookings, and manage reservations. It uses **JAX-WS Web Services** for communication between components, and follows a **multi-layer architecture** separating data access, business logic, and presentation layers.

### Project Structure

- **com.flightreservation.flightdao**:  
  - `FlightDao.java`: Interface for flight data operations.
  - `FlightDaoImpl.java`: Implementation of `FlightDao`.
  - `PassengerDao.java`, `PassengerDaoImpl.java`: Interfaces and implementations for managing passenger data.
  - `ReservationDao.java`, `ReservationDaoImpl.java`: Interfaces and implementations for reservation-related operations.
  - `UserDao.java`, `UserDaoImpl.java`: Interfaces and implementations for user-related database operations.

- **com.flightreservation.pages**:  
  - `LoginPage.java`: Handles user login.
  - `SearchFlightPage.java`: Allows users to search flights.
  - `PassengerDetailsPage.java`: Collects passenger details for booking.
  - `PaymentPage.java`: Manages payment process.
  - `ReservationPage.java`: Displays reservation details.
  
- **com.flightreservation.pojos**:  
  - `Flight.java`: Represents a flight.
  - `Passenger.java`: Represents a passenger.
  - `Reservation.java`: Represents a reservation.
  - `User.java`: Represents a user.
  - `Card.java`: Represents payment card information.

- **com.flightreservation.utils**:  
  - `DBUtils.java`: Utility class for database connections.

### Key Features:
- **Flight Search**: Users can search for flights based on destination and date.
- **Booking & Payment**: Users can book flights and complete payment.
- **User Management**: Registration and login functionality.

This system is designed to be simple yet scalable, ideal for handling flight reservations and can be extended with more features.
