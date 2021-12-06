package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;
import com.devsuperior.bds02.services.exceptions.DatabaseException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ControllerNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
		StandartError standartError = new StandartError();
		standartError.setTimestamp(Instant.now());
		standartError.setStatus(HttpStatus.NOT_FOUND.value());
		standartError.setError("Resource not found.");
		standartError.setMessage(e.getMessage());
		standartError.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandartError> entityNotFound(DatabaseException e, HttpServletRequest request) {
		StandartError standartError = new StandartError();
		standartError.setTimestamp(Instant.now());
		standartError.setStatus(HttpStatus.BAD_REQUEST.value());
		standartError.setError("Database exception.");
		standartError.setMessage(e.getMessage());
		standartError.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
	}

}
