package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Airport;

public interface AirportDao extends JpaRepository<Airport, String>{

}
