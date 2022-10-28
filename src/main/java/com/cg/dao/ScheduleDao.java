package com.cg.dao;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, BigInteger>{
	
}
  