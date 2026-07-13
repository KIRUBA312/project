package com.example.enterprise_iam.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public LocalDateTime now() {
		return LocalDateTime.now();
	}
	public LocalDateTime addMinutes(int minutes) {
		return LocalDateTime.now().plusMinutes(minutes);
	}
	public LocalDateTime addHours(int hours) {
		return LocalDateTime.now().plusHours(hours);
	}
	
	public LocalDateTime addDays(int days) {
		return LocalDateTime.now().plusDays(days);
	}
	public boolean isExpired(LocalDateTime expiryTime) {
		if(expiryTime == null) {
			return true;
		}
		return LocalDateTime.now().isAfter(expiryTime);
	}
	
	public long minutesBetween(LocalDateTime start,LocalDateTime end) {
		return ChronoUnit.MINUTES.between(start, end);
	}
	public long hoursBetween(LocalDateTime start,LocalDateTime end) {
		return ChronoUnit.HOURS.between(start, end);
	}
	
}
