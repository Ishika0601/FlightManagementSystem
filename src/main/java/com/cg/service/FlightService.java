package com.cg.service;

	
import java.math.BigInteger;
import java.util.List;
import com.cg.bean.Flight;

public interface FlightService {
	
	//add a flight
	public Flight addFlight(Flight flight);
	
	//modify a flight
	public Flight modifyFlight(Flight flight);
	
	//view flight by flight number
	public Flight viewFlight(BigInteger fn);
	
	//view all flights
	public List<Flight> viewFlight();
	
	//delete flight by flight number
	void deleteFlight(BigInteger fn);
	
	//validate flight
	void validateFlight(Flight flight);

}
