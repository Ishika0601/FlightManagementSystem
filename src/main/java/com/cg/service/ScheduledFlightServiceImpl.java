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
			//throw exception if scheduled flight not found
			throw new ScheduledFlightNotFoundException("No scheduled flight found");
		}
		ScheduledFlight b = optsf.get();
		
		b.setAvailableSeats(scheduledFlight.getAvailableSeats());
		b.setSchedule(scheduledFlight.getSchedule());
		return scheduledFlightDao.save(b);
	}
 
	
	@Override
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno) {
		
		List<ScheduledFlight> sflist = scheduledFlightDao.findByFlightFlightNumber(fno);
		if(sflist.size()==0)
		{
			//throw exception if scheduled flight not found
			throw new ScheduledFlightNotFoundException("No scheduled flight found for flight number "+fno);
		}
		return sflist;
	}

	
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
			//throw exception if scheduled flight not found
			throw new ScheduledFlightNotFoundException("No scheduled flight found for scheduled flight id "+sfid);
		}
		scheduledFlightDao.deleteById(sfid);
		
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
				//add scheduled flight in the list if it is between the given airports & available seats !=0
				sf2.add(s);
			}
		}
		if (sf2.size()==0) {
			//throw exception if not scheduled flight found
			throw new ScheduledFlightNotFoundException("No flight found between "+src+" and "+dst+" on "+date);
		}
		return sf2;
	}
	
	@Override
	public void validateScheduledFlight(ScheduledFlight scft) {
		
		//arrival & departure date time > current date time 
		if(scft.getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 || 
				scft.getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0 )
		{
			throw new InvalidScheduledFlightException("Date time entered has already elapsed");
		}
		
		//arrival > departure date time
		if (scft.getSchedule().getArrivalTime().compareTo(scft.getSchedule().getDepartureTime())<0)
		{
			throw new InvalidScheduledFlightException("Arrival time should be greater than the departure time");
		}
		//Destination & source airport should be there in the database
		List<Airport> a1 = airportDao.findAll();
		if(a1.stream().noneMatch(a -> a.getAirportCode().equals(scft.getSchedule().getSourceAirport().getAirportCode())) || 
				a1.stream().noneMatch(a -> a.getAirportCode().equals(scft.getSchedule().getDestinationAirport().getAirportCode())))
		{
			throw new InvalidScheduledFlightException("Airport does not exist in the database");
		}
		
		//Destination & source airport should not be same
		if (scft.getSchedule().getDestinationAirport().getAirportCode().equals(scft.getSchedule().getSourceAirport().getAirportCode())) 
		{
			throw new InvalidScheduledFlightException("Destination airport should not be same as source airport");
		}
		
	}
	
}
