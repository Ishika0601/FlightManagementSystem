package com.cg.service;

	
	import java.math.BigInteger;
	import java.util.List;

	import com.cg.bean.Flight;

	public interface FlightService {

		public Flight addFlight(Flight flight);
		public Flight modifyFlight(Flight flight);
		public Flight viewFlight( BigInteger fn);
		public List<Flight> viewFlight();   
		void deleteFlight(BigInteger fn);
		void validateFlight(Flight flight);

}
