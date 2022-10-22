package com.cg.bean;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule {
	
	@Id
	BigInteger sid;
	
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
	
	
	public Schedule(BigInteger sid, Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalTime,LocalDateTime departureTime) {
		super();
		this.sid = sid;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public BigInteger getSid() {
		return sid;
	}

	public void setSid(BigInteger sid) {
		this.sid = sid;
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


	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", sourceAirport=" + sourceAirport + ", destinationAirport="
				+ destinationAirport + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}
	
}
