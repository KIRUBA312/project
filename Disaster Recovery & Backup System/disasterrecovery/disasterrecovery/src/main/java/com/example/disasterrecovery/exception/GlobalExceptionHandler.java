package com.example.disasterrecovery.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.disasterrecovery.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BackupFailedException.class)
	public ResponseEntity<ErrorResponseDto> handleBackupFailedException(
			BackupFailedException ex){
		ErrorResponseDto dto = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(dto,
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(RestoreFailedException.class)
	public ResponseEntity<ErrorResponseDto> handleRestoreFailedException(
			RestoreFailedException ex){
		
		ErrorResponseDto dto = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(dto,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IntegrityCheckException.class)
	public ResponseEntity<ErrorResponseDto> handleIntegrityCheckException(
			IntegrityCheckException ex){
		ErrorResponseDto dto = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGenericException(
			Exception ex){
		ErrorResponseDto dto = new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				dto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
