package com.example.cdc_synchronization_engine.service;

public interface DeadLetterRetryService {



	void retryDeadLetterEvent(Long id);

}