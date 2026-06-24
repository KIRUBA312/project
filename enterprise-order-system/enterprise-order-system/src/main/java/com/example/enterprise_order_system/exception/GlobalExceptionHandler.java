package com.example.enterprise_order_system.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.enterprise_order_system.dto.ErrorResponseDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
			ResourceNotFoundException ex,
			HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(
				response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateOrderException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateOrder(
			DuplicateOrderException ex,HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<ErrorResponseDto> handlePaymentFailed(
			PaymentFailedException ex,HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(InventoryException.class)
	public ResponseEntity<ErrorResponseDto> handleInventoryException(
			InventoryException ex,HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponseDto> handleBusinessException(
			BusinessException ex,HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> habdleValidation(
			MethodArgumentNotValidException ex,
			HttpServletRequest request){
		String errorMessage = ex.getBindingResult()
				.getFieldError().getDefaultMessage();
		ErrorResponseDto response = new ErrorResponseDto(
				errorMessage,LocalDateTime.now(),request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(
			Exception ex,HttpServletRequest request){
		ErrorResponseDto response = new ErrorResponseDto(
				ex.getMessage(),LocalDateTime.now(),
				request.getRequestURI());
		return new ResponseEntity<>(response,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
