package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import com.cg.bean.Booking;
import com.cg.bean.Passenger;

public interface BookingService 
{	
	//add a booking
	public Booking addBooking(Booking booking);
	
	//modify a booking
	public Booking modifyBooking(Booking booking);
	
	//view booking by booking id
	public Booking viewBooking(BigInteger id);
	
	//view all bookings
	public List<Booking> viewBooking();
	
	//delete booking by booking id
	public void deleteBooking(BigInteger id);
	
	//validate booking
	public void validateBooking(Booking booking);
	
	//validate passenger
	public void validatePassenger(Passenger passenger);
}
