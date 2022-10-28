package com.cg.service;

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
import com.cg.bean.ScheduledFlight;
import com.cg.dao.AirportDao;
import com.cg.dao.BookingDao;
import com.cg.dao.ScheduledFlightDao;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.InvalidBookingException;
import com.cg.exception.InvalidScheduledFlightException;

@Service("bookingService")
public class BookingServiceImpl implements BookingService
{
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	AirportDao airportDao;
	
	@Autowired
	ScheduledFlightDao scheduledFlightDao;
	
	@Transactional
	@Override
	//add Booking
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		booking.setTicketCost(booking.getNoOfPassengers());
		ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-booking.getNoOfPassengers());
		return bookingDao.save(booking);
	
	}

	@Transactional
	@Override
	//Modify Booking
	public Booking modifyBooking(Booking booking) {
		// TODO Auto-generated method stub
		Optional<Booking> opbook = bookingDao.findById(booking.getBookingId());
		if(opbook.isEmpty())
		{
			throw new BookingNotFoundException("No booking found for booking id : "+booking.getBookingId());
		}
		Booking b = opbook.get();
		b.setBookingDate(booking.getBookingDate());
		b.setPassengerList(booking.getPassengerList());
		booking.setTicketCost(booking.getNoOfPassengers());
		int diff = booking.getNoOfPassengers() - b.getNoOfPassengers();
		ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-diff);
		b.setNoOfPassengers(booking.getNoOfPassengers());
		b.setTicketCost(b.getNoOfPassengers());
		return bookingDao.save(booking);
	}

	@Transactional
	@Override
	//View Booking by BookingId
	public Booking viewBooking(BigInteger id) {
		// TODO Auto-generated method stub
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isEmpty())
		{
			throw new BookingNotFoundException("No booking found for booking id : "+id);
		}
		return opbook.get();
		
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
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isPresent())
		{
			Booking booking = opbook.get();
			ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
			sf.setAvailableSeats(sf.getAvailableSeats()+booking.getNoOfPassengers());
			bookingDao.delete(booking);
		}
		else
		{
			throw new BookingNotFoundException("No booking found for booking id : "+id);
		}
		
	}
	
	@Transactional
	@Override
	//validateBooking
	public void validateBooking(Booking booking) {
		// TODO Auto-generated method stub
		Integer nop = booking.getNoOfPassengers();
		int availableSeats = booking.getFlight().getAvailableSeats();
		if(nop > availableSeats || nop>4)
		{
			throw new InvalidBookingException("Number of passengers are invalid");
		}
		
		if(booking.getFlight().getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 
				|| booking.getFlight().getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0 
				|| booking.getFlight().getSchedule().getArrivalTime().
				compareTo(booking.getFlight().getSchedule().getDepartureTime())<0)
		{
			throw new InvalidBookingException("Date and time has already elapsed");
		}
		
		List<Airport> a1 = airportDao.findAll();
		if(a1.stream().noneMatch(a -> a.getAirportCode().equals(booking.getFlight().getSchedule().getSourceAirport().getAirportCode())) || 
		a1.stream().noneMatch(a -> a.getAirportCode().equals(booking.getFlight().getSchedule().getDestinationAirport().getAirportCode())))
		{
			throw new InvalidBookingException("Airport does not exist in the database");
		}
		if (booking.getFlight().getSchedule().getDestinationAirport().equals(booking.getFlight().getSchedule().getSourceAirport())) 
		{
			throw new InvalidBookingException("Destination airport should not be same as arrival airport");
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
			throw new InvalidBookingException("Passenger UIN is invalid");
		}
		
		
	}
}
