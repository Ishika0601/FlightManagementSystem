package com.cg.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.advice.ErrorResponse;
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
		if(!fno.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Flight number should be a big integer");
    	}
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
		if(!fno.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Flight number should be a big integer");
    	}
		flightService.deleteFlight(fno);
	} 
	// local to the RestController
		 @ExceptionHandler(InputMismatchException.class)
		    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		        List<String> details = new ArrayList<>();
		        details.add(ex.getLocalizedMessage());
		        ErrorResponse error = new ErrorResponse("Server error from controller", details);
		        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
}
