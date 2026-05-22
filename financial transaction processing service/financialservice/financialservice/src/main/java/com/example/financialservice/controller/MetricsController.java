package com.example.financialservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financialservice.service.MetricsService;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

	@Autowired
	private MetricsService metricsService;
	
	@GetMapping
	public Map<String, Object> getMetrics(){
		Map<String, Object> map = new HashMap<>();
		map.put("totalTransactions", metricsService.totalTransactions());
		
		return map;
	}
	
}
