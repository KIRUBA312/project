package com.example.financialservice.constants;

public class AppConstants {
	
	private AppConstants() {}
	
	public static final String SECRET_KEY = 
			"mysecuresecretkeymysecuresecretekey123456";
	
	public static final long JWT_EXPIRATION = 1000*60*60*10;
	
	//account status
	public static final String ACTIVE ="ACTIVE";
	
	public static final String INACTIVE = "INACTIVE";
	
	public static final String INITIATED = "INITIATED";
	public static final String PROCESSING = "PROCESSING";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String REVERSED = "REVERSED";
	
	public static final String DEBIT = "DEBIT";
	public static final String CREDIT = "CREDIT";
	public static final String TRANSFER = "TRANSFER";
	
	public static final String ACCOUNT_NOT_FOUND = 
			"Account not found";
	public static final String USER_NOT_FOUND =
			"User not found";
	public static final String INSUFFICIENT_BALANCE =
			"Insufficient balance";
	public static final String FRAUD_DETECTED = "Fraud detected";
	
	public static final String DUPLICATE_REQUEST = 
			"Duplicate request detected";

}
