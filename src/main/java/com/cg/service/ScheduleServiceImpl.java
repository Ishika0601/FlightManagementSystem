package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<Schedule> s = scheduleDao.findBySourceAirportAirportName(name);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for source "+name);
		}
		return s;
	}

	@Override
	public List<Schedule> viewScheduleByDestinationName(String name) {
		List<Schedule> s = scheduleDao.findByDestinationAirportAirportName(name);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for destination "+name);
		}
		return s;
	}

	@Override
	public List<Schedule> viewScheduleByArrivalTime(LocalDateTime arrival) {
		List<Schedule> s = scheduleDao.findByArrivalTime(arrival);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for arrival time "+arrival);
		}
		return s;
	}

	@Override
	public List<Schedule> viewScheduleByDepartureTime(LocalDateTime departure) {
		List<Schedule> s = scheduleDao.findByDepartureTime(departure);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for departure time "+departure);
		}
		return s;
	}
	
	@Transactional
	@Override
	public Schedule addSchedule(Schedule schedule) {
		
		return scheduleDao.save(schedule);
	}
	
	@Transactional
	@Override
	public Schedule modifySchedule(BigInteger sid, Schedule schedule) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		Schedule s = sop.get();
		s.setSourceAirport(schedule.getSourceAirport());
		s.setDestinationAirport(schedule.getDestinationAirport());
		s.setArrivalTime(schedule.getArrivalTime());
		s.setDepartureTime(schedule.getDepartureTime());
		return scheduleDao.save(s);
	}

	@Transactional
	@Override
	public void deleteSchedule(BigInteger sid) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		scheduleDao.deleteById(sid);
	}

	@Transactional
	@Override
	public Schedule patchSchedule(BigInteger sid, Schedule schedule) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		Schedule s = sop.get();
		return null;
	}

	@Override
	public void validateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

}
