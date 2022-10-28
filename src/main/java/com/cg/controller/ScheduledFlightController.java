package com.cg.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.cg.bean.ScheduledFlight;
import com.cg.service.ScheduledFlightService;

@RestController
@RequestMapping("/scheduledflight")
public class ScheduledFlightController {

	@Autowired
	ScheduledFlightService scheduledFlightService;
	
	/*
	 URI : http://localhost:9001/scheduledflight/showAllSchFlights
	 METHOD : GET
	 */
	@GetMapping("/showAllSchFlights")
	public List<ScheduledFlight> showAllSchFlights()
	{
		return scheduledFlightService.viewScheduledFlight();
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/addSchFlight
	 METHOD : POST	 
	 */
	@PostMapping("/addSchFlight")
	public ScheduledFlight addSchFlights(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.scheduleFlight(newScheduledFlight);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/showByFno/10001
	 METHOD : GET
	 */
	@GetMapping("/showByFno/{fno}")
	public List<ScheduledFlight> showByFlightNo(@PathVariable BigInteger fno)
	{
		if(!fno.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Flight number should be a big integer");
    	}
		return scheduledFlightService.viewScheduledFlights(fno);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/modifySchFlight
	 METHOD : PUT
	 */
	@PutMapping("/modifySchFlight")
	public ScheduledFlight modifySchFlight(@RequestBody ScheduledFlight newScheduledFlight)
	{
		scheduledFlightService.validateScheduledFlight(newScheduledFlight);
		return scheduledFlightService.modifyScheduledFlight(newScheduledFlight);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/deleteSchFlight/100
	 METHOD : DELETE
	 */
	@DeleteMapping("/deleteSchFlight/{sfid}")
	public void deleteSchFlight(@PathVariable BigInteger sfid)
	{
		if(!sfid.getClass().getSimpleName().equals("BigInteger")) {
    		throw new InputMismatchException("Scheduled Flight Id should be a big integer");
    	}
		scheduledFlightService.deleteScheduledFlight(sfid);
	}
	
	/*
	 URI : http://localhost:9001/scheduledflight/showByAirport/Bangalore/Mumbai/2022-10-29
	 METHOD : GET
	 */
	@GetMapping("/showByAirport/{src}/{dsc}/{date}")
	public List<ScheduledFlight> showByAirport(@PathVariable String src,@PathVariable String dsc,@PathVariable String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  LocalDate localDate = LocalDate.parse(date, formatter);
		return scheduledFlightService.viewScheduledFlights(src,dsc,localDate);
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





/* POST REQUEST BODY
{
	  "availableSeats": 90,
	  "flight": {
	    "carrierName": "Indigo",
	    "flightModel": "Jet",
	    "flightNumber": 10001,
	    "seatCapacity": 100
	  },
	  "schedule": {
	    "arrivalTime": "2022-10-29 16:10:00",
	    "departureTime": "2022-10-29 14:10:00",
	    "destinationAirport": {
	      "airportCode": "102",
	      "airportLocation": "Mumbai",
	      "airportName": "Chhatrapati Shivaji International Airport"
	    },
	    "sid": 1,
	    "sourceAirport": {
	      "airportCode": "103",
	      "airportLocation": "Bangalore",
	      "airportName": "Kempegowda International Airport"
	    }
	  },
	  "sfid": 1
	}
*/	

/* UPDATE REQUEST BODY
{
  "availableSeats": 80,
  "flight": {
    "carrierName": "Indigo",
    "flightModel": "Jet",
    "flightNumber": 10001,
    "seatCapacity": 100
  },
  "schedule": {
    "arrivalTime": "2022-10-29 16:10:00",
    "departureTime": "2022-10-29 14:10:00",
    "destinationAirport": {
      "airportCode": "102",
      "airportLocation": "Mumbai",
      "airportName": "Chhatrapati Shivaji International Airport"
    },
    "sid": 100,
    "sourceAirport": {
      "airportCode": "103",
      "airportLocation": "Bangalore",
      "airportName": "Kempegowda International Airport"
    }
  },
  "sfid": 100
}
*/
