package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Airport;
import com.cg.bean.ScheduledFlight;


public interface ScheduledFlightService {
	public ScheduledFlight scheduleFlight(ScheduledFlight scft);
	
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno);
	
	public List<ScheduledFlight> viewScheduledFlight();
	
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);
	
	public void deleteScheduledFlight(BigInteger fno);
	
	public void validateScheduledFlight(ScheduledFlight scft);
	
	public List<ScheduledFlight> viewScheduledFlights(String src, String dst, LocalDate date);
}
