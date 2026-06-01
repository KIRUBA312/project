package com.example.stocktradingengine.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.stocktradingengine.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> 
	handleResourceNotFoundException(ResourceNotFoundException ex){
	
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(InvalidOrderException.class)
	public ResponseEntity<ErrorResponseDto> 
	handleInvalidOrderException(InvalidOrderException ex){

		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.BAD_REQUEST.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponseDto> 
	handleInsufficientBalanceException(InsufficientBalanceException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.BAD_REQUEST.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	
}
