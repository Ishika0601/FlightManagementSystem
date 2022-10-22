package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Airport;
import com.cg.bean.Flight;
import com.cg.bean.Schedule;
import com.cg.bean.ScheduledFlight;


public interface ScheduledFlightService {
	public ScheduledFlight scheduleFlight(ScheduledFlight scft);
	
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno);
	
	public List<ScheduledFlight> viewScheduledFlight();
	
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);
	//public ScheduledFlight modifyScheduledFlight(Flight flight,Schedule schedule, Integer avalseats);
	
	public void deleteScheduledFlight(BigInteger fno);
	
	public void validateScheduledFlight(ScheduledFlight scft);
	
	public List<ScheduledFlight> viewScheduledFlights(Airport src, Airport dst, LocalDate date);
}
