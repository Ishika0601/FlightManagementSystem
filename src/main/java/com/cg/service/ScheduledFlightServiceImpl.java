package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.ScheduledFlight;
import com.cg.dao.ScheduledFlightDao;

public class ScheduledFlightServiceImpl implements ScheduledFlightService {

	ScheduledFlightDao scheduledFlightDao;
	
	@Transactional
	@Override
	public ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) {
		return scheduledFlightDao.save(scheduledFlight);
	}
	
	@Transactional
	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight) {
		BigInteger fno = scheduledFlight.getFlight().getFlightNumber();
		Optional<ScheduledFlight> optac = scheduledFlightDao.findById(fno);
		ScheduledFlight b = optac.get();
		if(b == null)
		{
			//throw flight not found
		}
		b.setFlight(b.getFlight());
		b.setAvailableSeats(b.getAvailableSeats());
		b.setSchedule(b.getSchedule());
		return scheduledFlightDao.save(scheduledFlight);
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
