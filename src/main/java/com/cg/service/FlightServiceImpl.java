package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Booking;
import com.cg.bean.Flight;
import com.cg.dao.FlightDao;

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
	public Flight modifyFlight(Flight flight,BigInteger flightNumber) {
		// TODO Auto-generated method stub
		Optional<Flight> flt = flightDao.findById(flightNumber);
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
	public List<Flight> viewFlight(BigInteger fn) {
		// TODO Auto-generated method stub
		return flightDao.findAllById((Iterable<BigInteger>) fn);
		
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
	flightDao.deleteById(fn);
		
	}
}