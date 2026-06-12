package com.example.disasterrecovery.service;


public interface RetentionPolicyService {

	long getExpiredBackupCount();
	
	long deleteExpiredBackups();

	String executeRetentionPolicy();

}
