package com.cg.service;

import java.util.List;

import com.cg.bean.Airport;

public interface AirportService {
	
	public List<Airport> viewAirport();
	public Airport viewAirport(String code);
	
}
