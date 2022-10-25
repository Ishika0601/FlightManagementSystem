package com.cg.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.*;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(AirportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String airportNotFoundHandler(AirportNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(InvalidUserException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String userInvalidHandler(InvalidUserException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(ScheduledFlightNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String scheduledFlightNotFoundHandler(ScheduledFlightNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(InvalidScheduledFlightException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String scheduledFlightInvalidHandler(InvalidScheduledFlightException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(FlightNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String flightNotFoundHandler(FlightNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(InvalidFlightException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String flightInvalidHandler(InvalidFlightException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception e) {
		return e.getMessage();
	}
}
