package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.Booking;
import com.cg.bean.Flight;
import com.cg.bean.ScheduledFlight;
import com.cg.dao.FlightDao;
import com.cg.exception.InvalidScheduledFlightException;

public class FlightServiceImpl implements FlightService {
	
	@Autowired
     
	FlightDao flightDao;
	
	@Transactional
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		return flightDao.save(flight);
	}
	@Transactional
	@Override
	public Flight modifyFlight(Flight flight) {
		// TODO Auto-generated method stub
		Optional<Flight> flt = flightDao.findById(flight.getFlightNumber());
		Flight f = flt.get();
		if(f == null)
		{
			//throw flightnotfound
		}
     	f.setFlightNumber(f.getFlightNumber());
		f.setCarrierName(f.getCarrierName());
		f.setFlightModel(f.getFlightModel());
		return flightDao.save(flight);
	}
	
	@Transactional
	@Override
	public Flight viewFlight(BigInteger fn) {
		// TODO Auto-generated method stub
		Optional<Flight> flt = flightDao.findById(fn);
		Flight f = flt.get();
		if(f == null)
		{
			//throw flightnotfound
		}
		return f;
		
	}
	@Transactional
	@Override
	public List<Flight> viewFlight() {
		// TODO Auto-generated method stub
		return flightDao.findAll();
	}
    
	@Transactional
	@Override
	public void deleteFlight(BigInteger fn) {
		// TODO Auto-generated method stub
		Optional<Flight> flt = flightDao.findById(fn);
		Flight f = flt.get();
		if(f == null)
		{
			//throw flightnotfound
		}
	    flightDao.deleteById(fn);
		
	}
	
    @Override
	public void validateFlight(Flight flight) {
		
	if(flight.getFlightNumber()==null || !flight.getFlightNumber().getClass().getSimpleName().equals("BigInteger"))
	{
		throw new InvalidScheduledFlightException("Date time entered has already elapsed");
	}		
	
	}
	
	
	
}