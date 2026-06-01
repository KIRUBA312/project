package com.example.stocktradingengine.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderIdGenerator {

	private OrderIdGenerator() {}
	
	public static String generateOrderId() {
		
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyyMMddHHmmss");
		
		String timestamp = LocalDateTime.now().format(formatter);
		
		int randomNumber = new Random().nextInt(9000)+1000;
		
		return "ORD-"+timestamp+"-"+randomNumber;
		
	}
	
}
