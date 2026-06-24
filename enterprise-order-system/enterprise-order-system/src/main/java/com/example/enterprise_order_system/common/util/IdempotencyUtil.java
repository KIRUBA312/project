package com.example.enterprise_order_system.common.util;

import java.util.UUID;

public class IdempotencyUtil {

	private IdempotencyUtil() {
		
	}
	public static String generateKey() {
		return UUID.randomUUID().toString();
	}
}
