package com.cg.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport {
	
	@Id
	@Column(name="code")
	String airportCode;

	@Column(name="name")
	String airportName;
	
	@Column(name="location")
	String airportLocation;
	
	//Default unparameterized constructor
	public Airport() {
		
	}
	
	//Parameterized constructor
	public Airport(String airportCode,String airportName,String airportLocation) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.airportLocation = airportLocation;
	}

	//Getters & setters
	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	//toString method
	@Override
	public String toString() {
		return "Flight [airportCode=" + airportCode + ", airportName=" + airportName + ", airportLocation="
				+ airportLocation + "]";
	}

	
	
}
