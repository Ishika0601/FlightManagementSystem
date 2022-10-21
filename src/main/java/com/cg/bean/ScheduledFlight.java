package com.cg.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ScheduledFlight {
	
	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="flightNumber")
	Flight flight;
	
	@OneToOne(cascade=CascadeType.ALL)
	Schedule schedule;
	
	@Column(name="avalseat")
	int availableSeats;

	public ScheduledFlight() {
		
	}

	public ScheduledFlight(Flight flight, Schedule schedule, int availableSeats) {
		super();
		this.flight = flight;
		this.schedule = schedule;
		this.availableSeats = availableSeats;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "ScheduledFlight [flight=" + flight + ", schedule=" + schedule + ", availableSeats=" + availableSeats
				+ "]";
	}
	
	

}
