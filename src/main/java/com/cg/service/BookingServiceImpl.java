package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Booking;
import com.cg.bean.User;
import com.cg.dao.BookingDao;


public class BookingServiceImpl implements BookingService
{

	BookingDao bookingDao;
	
	@Transactional
	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingDao.save(booking);
	}

	@Transactional
	@Override
	public Booking modifyBooking(Booking booking,BigInteger bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> optac = bookingDao.findById(bookingId);
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
	public List<Booking> viewBooking(BigInteger id) {
		// TODO Auto-generated method stub
		return bookingDao.findAllById((Iterable<BigInteger>) id);
		
	}

	@Transactional
	@Override
	public List<Booking> viewBooking() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

	@Transactional
	@Override
	public void deleteBooking(BigInteger id) {
		// TODO Auto-generated method stub
		bookingDao.deleteById(id);
		
	}

}
