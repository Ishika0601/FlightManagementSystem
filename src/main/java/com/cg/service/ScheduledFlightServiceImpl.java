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

		Optional<ScheduledFlight> optac = scheduledFlightDao.findById(scheduledFlight.getSfid());
		ScheduledFlight b = optac.get();
		if(b == null)
		{
			throw new ScheduledFlightNotFoundException("No scheduled flight found");
		}
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
		Optional<ScheduledFlight> optac = scheduledFlightDao.findById(sfid);
		ScheduledFlight b = optac.get();
		if(b == null)
		{
			throw new ScheduledFlightNotFoundException("No scheduled flight found for scheduled flight id "+sfid);
		}
		scheduledFlightDao.deleteById(sfid);
		
	}

	@Override
	public void validateScheduledFlight(ScheduledFlight scft) {
		
		if(scft.getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 || scft.getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0 || scft.getSchedule().getArrivalTime().compareTo(scft.getSchedule().getDepartureTime())<0)
		{
			throw new InvalidScheduledFlightException("Date time entered has already elapsed");
		}
		
		List<Airport> a1 = airportDao.findAll();
		if((!a1.contains(scft.getSchedule().getDestinationAirport())) || (!a1.contains(scft.getSchedule().getSourceAirport())))
		{
			throw new InvalidScheduledFlightException("Airport does not exist in the database");
		}
		
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport src, Airport dst, LocalDate date) {
		List<ScheduledFlight> sf1 = scheduledFlightDao.findAll();
		List<ScheduledFlight> sf2 = new ArrayList<>();
		
		for(ScheduledFlight s : sf1)
		{
			if(s.getSchedule().getSourceAirport().equals(src) && s.getSchedule().getDestinationAirport().equals(dst) && date.compareTo(s.getSchedule().getDepartureTime().toLocalDate())==0)
			{
				sf2.add(s);
			}
		}
		return sf2;
	}
	
}
