package com.cg.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Booking;



public interface BookingDao extends JpaRepository<Booking, BigInteger>{

}
