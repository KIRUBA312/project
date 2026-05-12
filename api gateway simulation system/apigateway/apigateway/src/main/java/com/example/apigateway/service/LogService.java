package com.example.apigateway.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.apigateway.entity.RequestLog;

public interface LogService {

	RequestLog saveLog(RequestLog log);

	List<RequestLog> getAllLogs();

	RequestLog getLogById(Long id);

	void deleteLog(Long id);

}
