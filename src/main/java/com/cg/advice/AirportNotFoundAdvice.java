package com.cg.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.AirportNotFoundException;

@RestControllerAdvice
public class AirportNotFoundAdvice {
	@ExceptionHandler(AirportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String traineeNotFoundHandler(AirportNotFoundException e) {
		return e.getMessage();
	}
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception e) {
		return e.getMessage();
	}
}
