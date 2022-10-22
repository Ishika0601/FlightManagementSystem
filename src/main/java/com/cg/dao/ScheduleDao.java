package com.cg.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Airport;
import com.cg.bean.Schedule;
import com.cg.bean.ScheduledFlight;

public interface ScheduleDao extends JpaRepository<Schedule, BigInteger>{
	
}
  