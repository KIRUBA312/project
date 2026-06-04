package com.example.airbnbbooking.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.airbnbbooking.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
			ResourceNotFoundException ex){
		
		ErrorResponseDto error = new ErrorResponseDto();
		error.setError(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingConflictException.class)
	public ResponseEntity<ErrorResponseDto> handleBookingConflictException(
			BookingConflictException ex){
		
		ErrorResponseDto error = new ErrorResponseDto();
		error.setError(ex.getMessage());
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidBookingException.class)
	public ResponseEntity<ErrorResponseDto> handleInvalidBookingException(
			InvalidBookingException ex){
		ErrorResponseDto error = new ErrorResponseDto();
		error.setError(ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(
			Exception ex){
		ErrorResponseDto error = new ErrorResponseDto();
		error.setError(ex.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
