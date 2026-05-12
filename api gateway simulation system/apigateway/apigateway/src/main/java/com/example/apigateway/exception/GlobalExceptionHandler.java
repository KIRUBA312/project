package com.example.apigateway.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.apigateway.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidApiKeyException.class)
	public ResponseEntity<ErrorResponseDto> handleInvalidApiKeyException(
			InvalidApiKeyException ex){
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		
		errorResponse.setError(ex.getMessage());
		errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		errorResponse.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception ex){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		
		errorResponseDto.setError(ex.getMessage());
		errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponseDto.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponseDto>(errorResponseDto,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
