package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.Flight;
import com.cg.bean.Schedule;
import com.cg.bean.ScheduledFlight;
import com.cg.dao.FlightDao;
import com.cg.dao.ScheduleDao;
import com.cg.dao.ScheduledFlightDao;

@Service("scheduledFlightService")
public class ScheduledFlightServiceImpl implements ScheduledFlightService {

	@Autowired
	ScheduledFlightDao scheduledFlightDao;
	@Autowired
	FlightDao flightDao;
	@Autowired
	ScheduleDao scheduleDao;
	
	@Transactional
	@Override
	public ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) {
		return scheduledFlightDao.save(scheduledFlight);
	}
	
	@Transactional
	@Override
	public ScheduledFlight modifyScheduledFlight(Flight flight,Schedule sch, int avalseats)) {
		BigInteger fno = flight.getFlightNumber();
		Optional<Flight> optac = flightDao.findById(fno);
		Flight b = optac.get();
		if(b == null)
		{
			//throw flight not found
		}
		BigInteger sno = sch.getSid();
		Optional<Schedule> optac1 = scheduleDao.findById(sno);
		Schedule c = optac1.get();
		if(c == null)
		{
			//throw schedule not found
		}
		
		//b.setAvailableSeats(b.getAvailableSeats());
		//c.setSchedule(c.getSchedule());
		return scheduledFlightDao.save(flight);
	}

	@Transactional
	@Override
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno) {
		return scheduledFlightDao.findAllById((Iterable<BigInteger>) fno);
		
	}

	@Transactional
	@Override
	public List<ScheduledFlight> viewScheduledFlight() {
		return scheduledFlightDao.findAll();
	}

	@Transactional
	@Override
	public void deleteScheduledFlight(BigInteger fno) {
		scheduledFlightDao.deleteById(fno);
		
	}

	@Override
	public void validateScheduledFlight(ScheduledFlight scft) {
		
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport src, Airport dst, LocalDate date) {
		return null;
	}
	
}
