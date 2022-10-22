package com.cg.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public void addBooking(@RequestBody Booking newBooking)
	{
		bookingService.addBooking(newBooking);
	}
	
	@GetMapping("/bookings/{bookingId}")
	public List<Booking> showById(@PathVariable BigInteger bookingId)
	{
		return bookingService.viewBooking(bookingId);
	}
	
	@PutMapping("/bookings/{bookingId}")
	public Booking updateBooking(@RequestBody Booking updateBooking)
	{
		
		return bookingService.modifyBooking(updateBooking);
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public void deleteBooking(@PathVariable BigInteger bookingId)
	{
		bookingService.deleteBooking(bookingId);
	}
}
