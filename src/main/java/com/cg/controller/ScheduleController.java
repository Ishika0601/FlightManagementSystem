package com.cg.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Schedule;
import com.cg.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping("/showAllSchedules")
	public List<Schedule> showAllSchedule() {
		List<Schedule> slist = scheduleService.viewSchedule();
		return slist;
	}
	
	@GetMapping("/showById/{sid}")
	public Schedule showById(@PathVariable BigInteger sid) {
		return scheduleService.viewSchedule(sid);
	}
	
	@GetMapping("/showBySourceName/{srcName}")
	public List<Schedule> showBySourceName(@PathVariable String srcName) {
		return scheduleService.viewScheduleBySourceName(srcName);
	}
	
	@GetMapping("/showByDestinationName/{dstName}")
	public List<Schedule> showByDestinationName(@PathVariable String dstName) {
		return scheduleService.viewScheduleByDestinationName(dstName);
	}
	
	@GetMapping("/showByDestinationName/{dstName}")
	public List<Schedule> showByDestinationName(@PathVariable String dstName) {
		return scheduleService.viewScheduleByDestinationName(dstName);
	}
}
