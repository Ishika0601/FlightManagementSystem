package com.cg.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.AirportNotFoundException;
import com.cg.exception.InvalidScheduledFlightException;
import com.cg.exception.InvalidUserException;
import com.cg.exception.ScheduledFlightNotFoundException;
import com.cg.exception.UserNotFoundException;

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
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception e) {
		return e.getMessage();
	}
}
