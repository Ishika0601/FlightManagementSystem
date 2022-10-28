package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.Flight;
import com.cg.bean.Schedule;
import com.cg.bean.ScheduledFlight;
import com.cg.dao.AirportDao;
import com.cg.dao.FlightDao;
import com.cg.dao.ScheduleDao;
import com.cg.dao.ScheduledFlightDao;
import com.cg.exception.InvalidScheduledFlightException;
import com.cg.exception.ScheduledFlightNotFoundException;

@Service("scheduledFlightService")
public class ScheduledFlightServiceImpl implements ScheduledFlightService {

	@Autowired
	ScheduledFlightDao scheduledFlightDao;
	@Autowired
	FlightDao flightDao;
	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	AirportDao airportDao;
	
	
	@Transactional
	@Override
	public ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) {
		return scheduledFlightDao.save(scheduledFlight);
	}
	
	@Transactional
	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight) {

		Optional<ScheduledFlight> optsf = scheduledFlightDao.findById(scheduledFlight.getSfid());	
		if(optsf.isEmpty())
		{
			throw new ScheduledFlightNotFoundException("No scheduled flight found");
		}
		ScheduledFlight b = optsf.get();
		Schedule c = scheduleDao.findById(b.getSchedule().getSid()).get();
		
		b.setAvailableSeats(scheduledFlight.getAvailableSeats());
		c.setSourceAirport(scheduledFlight.getSchedule().getSourceAirport());
		c.setDestinationAirport(scheduledFlight.getSchedule().getDestinationAirport());
		c.setArrivalTime(scheduledFlight.getSchedule().getArrivalTime());
		c.setDepartureTime(scheduledFlight.getSchedule().getDepartureTime());
		
		return scheduledFlightDao.save(b);
	}
 
	@Transactional
	@Override
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno) {
		
		List<ScheduledFlight> sflist = scheduledFlightDao.findByFlightFlightNumber(fno);
		if(sflist.size()==0)
		{
			throw new ScheduledFlightNotFoundException("No scheduled flight found for flight number "+fno);
		}
		return sflist;
	}

	@Transactional
	@Override
	public List<ScheduledFlight> viewScheduledFlight() {
		return scheduledFlightDao.findAll();
	}

	@Transactional
	@Override
	public void deleteScheduledFlight(BigInteger sfid) {
		Optional<ScheduledFlight> optsf = scheduledFlightDao.findById(sfid);
		if(optsf.isEmpty())
		{
			throw new ScheduledFlightNotFoundException("No scheduled flight found for scheduled flight id "+sfid);
		}
		scheduledFlightDao.deleteById(sfid);
		
	}

	@Override
	public void validateScheduledFlight(ScheduledFlight scft) {
		
		if(scft.getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 || 
				scft.getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0 || 
				scft.getSchedule().getArrivalTime().compareTo(scft.getSchedule().getDepartureTime())<0)
		{
			throw new InvalidScheduledFlightException("Date time entered has already elapsed");
		}
		
		List<Airport> a1 = airportDao.findAll();
		if(a1.stream().noneMatch(a -> a.getAirportCode().equals(scft.getSchedule().getSourceAirport().getAirportCode())) || 
				a1.stream().noneMatch(a -> a.getAirportCode().equals(scft.getSchedule().getDestinationAirport().getAirportCode())))
		{
			throw new InvalidScheduledFlightException("Airport does not exist in the database");
		}
		if (scft.getSchedule().getDestinationAirport().equals(scft.getSchedule().getSourceAirport())) 
		{
			throw new InvalidScheduledFlightException("Destination airport should not be same as arrival airport");
		}
		
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(String src, String dst, LocalDate date) {
		List<ScheduledFlight> sf1 = scheduledFlightDao.findAll();
		List<ScheduledFlight> sf2 = new ArrayList<>();
		
		for(ScheduledFlight s : sf1)
		{
			if(s.getSchedule().getSourceAirport().getAirportLocation().equals(src) && 
					s.getSchedule().getDestinationAirport().getAirportLocation().equals(dst) && 
					date.compareTo(s.getSchedule().getDepartureTime().toLocalDate())==0
					&& s.getAvailableSeats()!=0)
			{
				sf2.add(s);
			}
		}
		if (sf2.size()==0) {
			throw new ScheduledFlightNotFoundException("No flight found between "+src+" and "+dst+" on "+date);
		}
		return sf2;
	}
	
}
