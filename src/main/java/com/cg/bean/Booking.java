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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Booking 
{
	
	@Id
	@SequenceGenerator(name="sequence", initialValue=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@Column(name="id")
	BigInteger bookingId;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
	User user;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2021-08-20 00:00:00")
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
		this.ticketCost = BigDecimal.valueOf(6000*noOfPassengers);
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

	public void setUser(User user) {
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

	public void setTicketCost(Integer noOfPassengers) {
		this.ticketCost = BigDecimal.valueOf(6000*noOfPassengers);
	}

	public ScheduledFlight getFlight() {
		return flight;
	}

	public void setFlight(ScheduledFlight flight) {
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
