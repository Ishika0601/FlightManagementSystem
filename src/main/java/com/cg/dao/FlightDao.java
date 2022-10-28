package com.cg.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Flight;



public interface FlightDao extends JpaRepository<Flight, BigInteger>{

}
