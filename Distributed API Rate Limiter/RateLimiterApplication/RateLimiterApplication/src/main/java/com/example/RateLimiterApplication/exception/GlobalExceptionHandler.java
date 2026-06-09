package com.example.RateLimiterApplication.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.RateLimiterApplication.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RateLimitExceededException.class)
	public ResponseEntity<ErrorResponseDto> handleRateLimitExceededException(
			RateLimitExceededException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		dto.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(dto, HttpStatus.TOO_MANY_REQUESTS);
		
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto>
	handleResourceNotFoundException(
	        ResourceNotFoundException ex) {

	    ErrorResponseDto dto =
	            new ErrorResponseDto();

	    dto.setError(ex.getMessage());
	    dto.setStatus(HttpStatus.NOT_FOUND.value());
	    dto.setTimestamp(LocalDateTime.now());

	    return new ResponseEntity<>(
	            dto,
	            HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponseDto>
	handleRuntimeException(RuntimeException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto>
	handleException(Exception ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setError(ex.getMessage());
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
