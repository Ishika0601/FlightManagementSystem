package com.cg.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Passenger;

public interface BookingService 
{
	public Booking addBooking(Booking booking);
	public Booking modifyBooking(Booking booking);
	public Booking viewBooking(BigInteger id);
	public List<Booking> viewBooking();
	public void deleteBooking(BigInteger id);
	public void validateBooking(Booking booking);
	public void validatePassenger(Passenger passenger);
}
