package com.example.apigateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apigateway.entity.RequestLog;
import com.example.apigateway.repository.RequestLogRepository;
import com.example.apigateway.service.LogService;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private RequestLogRepository requestLogRepository;

	@Override
	public RequestLog saveLog(RequestLog log) {
		// TODO Auto-generated method stub
		return requestLogRepository.save(log);
		
	}

	@Override
	public List<RequestLog> getAllLogs() {
		// TODO Auto-generated method stub
		return requestLogRepository.findAll();
	}

	@Override
	public RequestLog getLogById(Long id) {
		// TODO Auto-generated method stub
		
		return requestLogRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Log Not Found"));
	}

	@Override
	public void deleteLog(Long id) {
		// TODO Auto-generated method stub
		requestLogRepository.deleteById(id);
		
	}
	
}
