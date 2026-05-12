package com.example.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apigateway.entity.RequestLog;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long>{

}
