package com.cg.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.ScheduledFlight;

public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, BigInteger>{

}
  