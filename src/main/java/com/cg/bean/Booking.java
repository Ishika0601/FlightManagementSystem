package com.cg.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking 
{
	
	@Id
	@SequenceGenerator(name="sequence", initialValue=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@Column(name="id")
	BigInteger bookingId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
	User user;
	
	@Column(name="date")
	LocalDateTime bookingDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bookingId",nullable = false)
	List<Passenger> passengerList = new ArrayList<Passenger>();
	
	@Column(name="cost")
	BigDecimal ticketCost;
	
	@OneToOne
	ScheduledFlight flight;
	
	@Column(name="nop")
	Integer noOfPassengers;
	
	//Default Constructor
	Booking()
	{
		
	}
	
	//Parameterized Constructor
	public Booking(BigInteger bookingId, User user, LocalDateTime bookingDate, List<Passenger> passengerList,BigDecimal ticketCost, ScheduledFlight flight, Integer noOfPassengers) {
		this.bookingId = bookingId;
		this.user = user;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.ticketCost = ticketCost;
		this.flight = flight;
		this.noOfPassengers = noOfPassengers;
	}
	
	
	//Getter and Setters
	public BigInteger getBookingId() {
		return bookingId;
	}

	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public BigDecimal getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(int nop) {
		
		this.ticketCost = BigDecimal.valueOf(10000*nop);
	}

	public ScheduledFlight getScheduledFlight() {
		return flight;
	}

	public void setScheduledFlight(ScheduledFlight flight) {
		this.flight = flight;
	}

	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	//toString Method
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + user + ", bookingDate=" + bookingDate
				+ ", passengerList=" + passengerList + ", ticketCost=" + ticketCost + ", noOfPassengers="
				+ noOfPassengers + "]";
	}

	
}
