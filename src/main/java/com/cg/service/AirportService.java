package com.cg.service;

import java.util.List;
import com.cg.bean.Airport;

public interface AirportService {
	
	//view all airports
	public List<Airport> viewAirport();
	
	//view airport by airport code
	public Airport viewAirport(String code);
	
}
