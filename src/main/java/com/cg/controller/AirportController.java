package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Airport;
import com.cg.service.AirportService;

@RestController
@RequestMapping("/airport")
public class AirportController {
	@Autowired
	AirportService airportService;
	
	@GetMapping("/showAllAirports")
	public List<Airport> showAllAirport() {
		List<Airport> alist = airportService.viewAirport();
		return alist;
	}
	
	@GetMapping("/showById/{code}")
	public Airport showById(@PathVariable String code) {
		return airportService.viewAirport(code);
	}
}
