package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.bean.Flight;
import com.cg.dao.FlightDao;
import com.cg.exception.FlightNotFoundException;
import com.cg.exception.InvalidFlightException;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	FlightDao flightDao;
	
	@Transactional
	@Override
	public Flight addFlight(Flight flight) {
		
		return flightDao.save(flight);
	}
	
	@Transactional
	@Override
	public Flight modifyFlight(Flight flight) { 
		Optional<Flight> flt = flightDao.findById(flight.getFlightNumber());		
		if(flt.isEmpty())
		{
			//throw exception if flight not found
			throw new FlightNotFoundException("No flight found with flight number "+flight.getFlightNumber());
		}
		Flight f = flt.get();
     	f.setFlightNumber(f.getFlightNumber());
		f.setCarrierName(f.getCarrierName());
		f.setFlightModel(f.getFlightModel());
		return flightDao.save(flight);
	}
	
	
	@Override
	public Flight viewFlight(BigInteger fn) {
		Optional<Flight> flt = flightDao.findById(fn);
		if(flt.isEmpty())
		{
			//throw exception if flight not found
			throw new FlightNotFoundException("No flight found with flight number "+fn);
		}
		return flt.get();
		
	}
	
	@Override
	public List<Flight> viewFlight() {
		
		return flightDao.findAll();
	}
    
	@Transactional
	@Override
	public void deleteFlight(BigInteger fn) {
		Optional<Flight> flt = flightDao.findById(fn);
		if(flt.isEmpty())
		{
			//throw exception if flight not found
			throw new FlightNotFoundException("No flight found with flight number "+fn);
		}
	    flightDao.deleteById(fn);
		
	}
	
    @Override
	public void validateFlight(Flight flight) {
		
    	//flight number should not be null and should be a big integer
    	if(flight.getFlightNumber()==null || !flight.getFlightNumber().getClass().getSimpleName().equals("BigInteger")){
    		throw new InvalidFlightException("Flight number not entered properly");
    	}		
	
	}	
	
}