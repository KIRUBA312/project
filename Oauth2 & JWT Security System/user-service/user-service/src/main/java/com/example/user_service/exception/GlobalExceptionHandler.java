package com.example.user_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.user_service.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
			ResourceNotFoundException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponseDto> handleUnauthorized(
			UnauthorizedException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.UNAUTHORIZED.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(
			Exception ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
