package com.cg.bean;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule {
	
	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="airportCode")
	@Column(name="src")
	Airport sourceAirport;
	
	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="airportCode")
	@Column(name="dst")
	Airport destinationAirport;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="atime")
	LocalDateTime arrivalTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dtime")
	LocalDateTime departureTime;
	
	//Default constructor
	public Schedule() {
		
	}
	
	//Parameterized constructor
	public Schedule(Airport sourceAirport, Airport destinationAirport,LocalDateTime arrivalTime,LocalDateTime departureTime) {
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}
	
	//Getters and setters
	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Schedule [sourceAirport=" + sourceAirport + ", destinationAirport=" + destinationAirport
				+ ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}
	
	
	
}
