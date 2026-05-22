package com.example.financialservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.financialservice.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
			ResourceNotFoundException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FraudDetectedException.class)
	public ResponseEntity<ErrorResponseDto> handleFraudDetectedException(
			FraudDetectedException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateRequestException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateRequestException(
			DuplicateRequestException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),HttpStatus.CONFLICT.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<ErrorResponseDto> handleInvalidTransactionException(
			InvalidTransactionException ex){
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleValidationException(
			MethodArgumentNotValidException ex){
		
		String message = ex.getBindingResult().getFieldError()
				.getDefaultMessage();
		
		ErrorResponseDto error = new ErrorResponseDto(
				message,HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex){
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
}
