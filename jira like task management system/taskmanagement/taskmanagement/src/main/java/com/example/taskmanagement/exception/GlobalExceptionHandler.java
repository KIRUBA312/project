package com.example.taskmanagement.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.taskmanagement.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
			ResourceNotFoundException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateResourceException(
			DuplicateResourceException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.CONFLICT.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponseDto> handleInvalidRequestException(
			InvalidRequestException ex){
		
		ErrorResponseDto error = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(
			MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error ->{
			String fileName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fileName, errorMessage);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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
