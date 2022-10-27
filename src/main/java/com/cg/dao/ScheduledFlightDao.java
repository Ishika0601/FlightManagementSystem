package com.cg.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Airport;
import com.cg.bean.ScheduledFlight;

public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, BigInteger>{
	
	List<ScheduledFlight> findByFlightFlightNumber(BigInteger fno);
	
	//List<ScheduledFlight> viewScheduledFlights(String src, String dst, LocalDate date);
}
  