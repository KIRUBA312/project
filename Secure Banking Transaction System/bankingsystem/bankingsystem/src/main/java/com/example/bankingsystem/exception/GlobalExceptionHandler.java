package com.example.bankingsystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.bankingsystem.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto>
	handleResourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponseDto>
	handleInsufficientBalanceException(InsufficientBalanceException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,
				HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidTransferException.class)
	public ResponseEntity<ErrorResponseDto>
	handleInvalidTransferException(InvalidTransferException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto>
	handleGlobalException(Exception ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
