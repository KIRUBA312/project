package com.example.auth_server.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.auth_server.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(
			UserNotFoundException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(TenantNotFoundException.class)
	public ResponseEntity<ErrorResponseDto>
	handleTenantNotFoundException(TenantNotFoundException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ErrorResponseDto> handleAuthenticationFailedException(
			AuthenticationFailedException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponseDto>
	handleInvalidTokenException(InvalidTokenException ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(
			Exception ex){
		ErrorResponseDto dto = new ErrorResponseDto();
		
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		dto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
