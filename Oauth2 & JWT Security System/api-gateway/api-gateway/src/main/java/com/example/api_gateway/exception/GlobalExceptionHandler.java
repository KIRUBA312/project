package com.example.api_gateway.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Map<String, Object> handleException(Exception ex){
		Map<String, Object> map = new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("timestamp", LocalDateTime.now());
		return map;
	}
	
}
