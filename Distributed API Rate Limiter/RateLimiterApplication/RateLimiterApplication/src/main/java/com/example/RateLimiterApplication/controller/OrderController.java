package com.example.RateLimiterApplication.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@PostMapping
	public Map<String, Object> createOrder(){
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Order Created Successfully");
		response.put("timestamp", LocalDateTime.now());
		
		return response;
	}
}
