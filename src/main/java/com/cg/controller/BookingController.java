package com.cg.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.advice.ErrorResponse;
import com.cg.bean.Booking;
import com.cg.service.BookingService;

@RestController
public class BookingController 
{

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/bookings")
	public List<Booking> showAllBookings()
	{
		return bookingService.viewBooking();
	}
	
	@PostMapping("/bookings")
	public Booking addBooking(@RequestBody Booking newBooking)
	{
		bookingService.validateBooking(newBooking);
		return bookingService.addBooking(newBooking);
	}
	
	@GetMapping("/bookings/{bookingId}")
	public Booking showById(@PathVariable BigInteger bookingId)
	{
		if(!bookingId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Booking Id should be a big integer");
    	}
		return bookingService.viewBooking(bookingId);
	}
	
	@PutMapping("/bookings")
	public Booking updateBooking(@RequestBody Booking updateBooking)
	{
		bookingService.validateBooking(updateBooking);
		return bookingService.modifyBooking(updateBooking);
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public void deleteBooking(@PathVariable BigInteger bookingId)
	{
		if(!bookingId.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Booking Id should be a big integer");
    	}
		bookingService.deleteBooking(bookingId);
	}
	// local to the RestController
			 @ExceptionHandler(InputMismatchException.class)
			    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
			        List<String> details = new ArrayList<>();
			        details.add(ex.getLocalizedMessage());
			        ErrorResponse error = new ErrorResponse("Server error from controller", details);
			        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
}
