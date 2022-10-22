package com.cg.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Airport;
import com.cg.bean.ScheduledFlight;
import com.cg.service.ScheduledFlightService;

@RestController
public class ScheduledFlightController {

	@Autowired
	ScheduledFlightService scheduledFlightService;
	
	@GetMapping("/scheduledflights")
	public List<ScheduledFlight> showAllSchFlights()
	{
		return scheduledFlightService.viewScheduledFlight();
	}
	
	@PostMapping("/scheduledflights")
	public void addSchFlights(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		scheduledFlightService.scheduleFlight(newScheduledFlight);
	}
	
	@GetMapping("/scheduledflightsByFno/{fno}")
	public List<ScheduledFlight> showByFlightNo(@PathVariable BigInteger fno)
	{
		return scheduledFlightService.viewScheduledFlights(fno);
	}
	
	@PutMapping("/scheduledflights")
	public ScheduledFlight modifySchFlight(@RequestBody ScheduledFlight newScheduledFlight)
	{
		
		return scheduledFlightService.modifyScheduledFlight(newScheduledFlight);
	}
	
	@DeleteMapping("/bookings/{sfid}")
	public void deleteSchFlight(@PathVariable BigInteger sfid)
	{
		scheduledFlightService.deleteScheduledFlight(sfid);
	}
	
	@GetMapping("/scheduledflightsByAirport/{src}/{dsc}/{date}")
	public List<ScheduledFlight> showByAirport(@PathVariable Airport src,@PathVariable Airport dsc,@PathVariable LocalDate date)
	{
		return scheduledFlightService.viewScheduledFlights(src,dsc,date);
	}
	
	
}
