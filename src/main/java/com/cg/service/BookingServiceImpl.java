package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Booking;
import com.cg.bean.Passenger;
import com.cg.bean.User;
import com.cg.dao.BookingDao;

public class BookingServiceImpl implements BookingService
{

BookingDao bookingDao;
	
	@Transactional
	@Override
	//add Booking
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		Optional<Booking> findBookingById = bookingDao.findById(booking.getBookingId());
		if(findBookingById.isPresent())
		{
			//throw Exception
		}
		return bookingDao.save(booking);
	
	}

	@Transactional
	@Override
	//Modify Booking
	public Booking modifyBooking(Booking booking) {
		// TODO Auto-generated method stub
		Optional<Booking> optac = bookingDao.findById(booking.getBookingId());
		Booking b = optac.get();
		if(b == null)
		{
			//throw bookingnotfound
		}
		b.setBookingId(b.getBookingId());
		b.setUserId(b.getUserId());
		b.setBookingDate(b.getBookingDate());
		b.setPassengerList(b.getPassengerList());
		b.setTicketCost(b.getTicketCost());
		b.setScheduledFlight(b.getScheduledFlightFlight());
		return bookingDao.save(booking);
	}

	@Transactional
	@Override
	//View Booking by BookingId
	public List<Booking> viewBooking(BigInteger id) {
		// TODO Auto-generated method stub
		Optional<Booking> bookingId = bookingDao.findById(id);
		if(!bookingId.isPresent())
		{
			//throw Exception
		}
		//Doubt
		return bookingDao.findAllById((List<BigInteger>) id);
		
	}

	@Transactional
	@Override
	//view All bookings
	public List<Booking> viewBooking() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

	@Transactional
	@Override
	//delete booking
	public void deleteBooking(BigInteger id) {
		// TODO Auto-generated method stub
		Optional<Booking> bookingId = bookingDao.findById(id);
		if(bookingId.isPresent())
		{
		bookingDao.deleteById(id);
		}
		else
		{
			//Exception
		}
		
	}
	
	@Transactional
	@Override
	//validateBooking
	public void validateBooking(Booking booking) {
		// TODO Auto-generated method stub
		Integer nop = booking.getNoOfPassengers();
		int availableSeats = booking.getScheduledFlightFlight().getAvailableSeats();
		if(nop > availableSeats)
		{
			//Exception
		}
		
	}

	@Transactional
	@Override
	//validatePasssenger
	public void validatePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		BigInteger uin = passenger.getPassengerUIN();
		Pattern p= Pattern.compile("^[1-9][0-9]{11}$");
		Matcher m=p.matcher(uin.toString());
		if(!m.find())
		{
			//exception
		}
		
		
	}
}
