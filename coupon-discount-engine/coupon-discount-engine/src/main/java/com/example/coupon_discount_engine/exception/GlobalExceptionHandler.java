package com.example.coupon_discount_engine.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFound(
			ResourceNotFoundException ex,HttpServletRequest request){
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.NOT_FOUND.value());
		response.put("error", "Not Found");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DuplicateCouponException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateCoupon(
			DuplicateCouponException ex,HttpServletRequest request){
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.CONFLICT.value());
		response.put("error", "Duplicate Coupon");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(CouponExpiredException.class)
	public ResponseEntity<Map<String, Object>> handleCouponExpired(
			CouponExpiredException ex,HttpServletRequest request){
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", "Coupon Expired");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CouponAlreadyUsedException.class)
	public ResponseEntity<Map<String, Object>> handleCouponAlreadyUsed(
			CouponAlreadyUsedException ex,HttpServletRequest request){
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", "Coupon Already Used");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCouponException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidCoupon(
			InvalidCouponException ex,HttpServletRequest request){
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", "Invalid Coupon");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> 
	handleValidationException(
			MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
		Map<String, String> errors = new HashMap<>();
		
		for(FieldError error: ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(),error.getDefaultMessage());
		}
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", errors);
		response.put("path", request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(
			Exception ex,HttpServletRequest request){
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("error", "Internal Server Error");
		response.put("message", ex.getMessage());
		response.put("path", request.getRequestURI());
		
		return new ResponseEntity<>(response,
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
