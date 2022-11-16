package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Schedule;
import com.cg.dao.ScheduleDao;
import com.cg.exception.ScheduleNotFoundException;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public List<Schedule> viewSchedule() {
		return scheduleDao.findAll();
	}

	@Override
	public Schedule viewSchedule(BigInteger sid) {
		Optional<Schedule> sc = scheduleDao.findById(sid);
		if (sc.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with code "+sid);
		}
		return sc.get();
	}

	@Override
	public List<Schedule> viewScheduleBySourceName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> viewScheduleByDestinationName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> viewScheduleByArrivalTime(LocalDateTime arrival) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> viewScheduleByDepartureTime(LocalDateTime departure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schedule modifySchedule(BigInteger sid, Schedule schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSchedule(BigInteger sid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Schedule patchSchedule(BigInteger sid, Schedule schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

}
