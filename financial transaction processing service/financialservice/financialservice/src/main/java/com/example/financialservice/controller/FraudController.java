package com.example.financialservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.financialservice.service.FraudService;

@RestController
@RequestMapping("/api/fraud")
public class FraudController {

	@Autowired
	private FraudService fraudService;
	
	@GetMapping("/validate")
	public ResponseEntity<Map<String, Object>>
	validateFraudRule(@RequestParam BigDecimal amount){
		
		fraudService.validateFraudRules(amount);
		Map<String, Object> response = new HashMap<>();
		
		response.put("message",
				"Transaction passed fraud validation");
		
		response.put("amount",amount);
		
		return ResponseEntity.ok(response);
		
	}
	
}
