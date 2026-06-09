package com.example.RateLimiterApplication.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@GetMapping("/api/products")
	public List<String> getProducts(){
		return Arrays.asList("Laptop","Mobile","Keyboard","Monitor");
	}

}
