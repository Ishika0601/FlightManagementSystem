package com.cg.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.Booking;
import com.cg.bean.Passenger;
import com.cg.bean.User;
import com.cg.dao.AirportDao;
import com.cg.dao.BookingDao;
import com.cg.exception.InvalidScheduledFlightException;

@Service("bookingService")
public class BookingServiceImpl implements BookingService
{
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	AirportDao airportDao;
	
	@Transactional
	@Override
	//add Booking
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		Integer n = booking.getNoOfPassengers();
		booking.setTicketCost(n);
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
		b.setBookingDate(booking.getBookingDate());
		b.setPassengerList(booking.getPassengerList());
		b.setTicketCost(booking.getNoOfPassengers());
		b.setNoOfPassengers(booking.getNoOfPassengers());
		return bookingDao.save(booking);
	}

	@Transactional
	@Override
	//View Booking by BookingId
	public Booking viewBooking(BigInteger id) {
		// TODO Auto-generated method stub
		Optional<Booking> bookingId = bookingDao.findById(id);
		if(!bookingId.isPresent())
		{
			//throw Exception
		}
		//Doubt
		return bookingId.get();
		
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
		int availableSeats = booking.getScheduledFlight().getAvailableSeats();
		if(nop > availableSeats)
		{
			//Exception
		}
		
		if(booking.getScheduledFlight().getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 
				|| booking.getScheduledFlight().getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0 
				|| booking.getScheduledFlight().getSchedule().getArrivalTime().
				compareTo(booking.getScheduledFlight().getSchedule().getDepartureTime())<0)
		{
			throw new InvalidScheduledFlightException("Date time entered has already elapsed");
		}
		
		List<Airport> a1 = airportDao.findAll();
		if((!a1.contains(booking.getScheduledFlight().getSchedule().getDestinationAirport())) || 
				(!a1.contains(booking.getScheduledFlight().getSchedule().getSourceAirport())))
		{
			throw new InvalidScheduledFlightException("Airport does not exist in the database");

		}
		for(Passenger p:booking.getPassengerList())
		{
			validatePassenger(p);
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
