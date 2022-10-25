package com.cg.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Flight;
import com.cg.service.FlightService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController 

public class FlightController {

	@Autowired
	FlightService flightService;
	
	@GetMapping("/flight")
	public List<Flight> showAllFlights()
	{
		return flightService.viewFlight();
	}
	
	@PostMapping ("/flight")
	public Flight addFlight(@RequestBody Flight newFlight) {
		flightService.validateFlight(newFlight);
		return flightService.addFlight(newFlight);
	}
	
	@GetMapping("/flight/{fno}")
	public Flight showById(@PathVariable BigInteger fno) 
	{
		return flightService.viewFlight(fno);
	}
	
	@PutMapping("/flight")
	public Flight modifyFlight(@RequestBody Flight  modifyFlight )
	{
		return flightService.modifyFlight(modifyFlight);
	}
	
	@DeleteMapping("/flight/{fno}")
	public void deleteFlight(@PathVariable BigInteger fno)
	{
		flightService.deleteFlight(fno);
	} 
}
