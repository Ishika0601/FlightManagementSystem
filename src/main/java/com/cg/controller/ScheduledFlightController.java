package com.cg.controller;

import java.math.BigInteger;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.advice.ErrorResponse;
import com.cg.bean.Airport;
import com.cg.bean.ScheduledFlight;
import com.cg.service.ScheduledFlightService;

@RestController
@RequestMapping("/scheduledflight")
public class ScheduledFlightController {

	@Autowired
	ScheduledFlightService scheduledFlightService;
	
	@GetMapping("/showAllSchFlights")
	public List<ScheduledFlight> showAllSchFlights()
	{
		return scheduledFlightService.viewScheduledFlight();
	}
	
	@PostMapping("/addSchFlight")
	public ScheduledFlight addSchFlights(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.scheduleFlight(newScheduledFlight);
	}
	
	@GetMapping("/showByFno/{fno}")
	public List<ScheduledFlight> showByFlightNo(@PathVariable BigInteger fno)
	{
		if(!fno.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Flight number should be a big integer");
    	}
		return scheduledFlightService.viewScheduledFlights(fno);
	}
	
	@PutMapping("/modifySchFlight")
	public ScheduledFlight modifySchFlight(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.modifyScheduledFlight(newScheduledFlight);
	}
	
	@DeleteMapping("/deleteSchFlight/{sfid}")
	public void deleteSchFlight(@PathVariable BigInteger sfid)
	{
		if(!sfid.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Scheduled Flight Id should be a big integer");
    	}
		scheduledFlightService.deleteScheduledFlight(sfid);
	}
	
	@GetMapping("/showByAirport/{src}/{dsc}/{date}")
	public List<ScheduledFlight> showByAirport(@PathVariable Airport src,@PathVariable Airport dsc,@PathVariable LocalDate date)
	{
		return scheduledFlightService.viewScheduledFlights(src,dsc,date);
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
