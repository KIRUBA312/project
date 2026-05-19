package com.example.bankingsystem.constants;

public class AppConstants {

	public static final String SECRET_KEY =
			"mysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";
	
	public static final long JWT_EXPIRATION = 1000*60*60;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER  = "USER";
	public static final String TRANSFER = "TRANSFER";
	public static final String ACCOUNT_CREATED = "ACCOUNT CREATED";
	public static final String ACCOUNT_UPDATED = "ACCOUNT UPDATED";
	public static final String ACCOUNT_DELETED = "ACCOUNT DELETED";
	public static final String TRANSACTION_SUCCESS = "Transfer Successful";
	
	private AppConstants() {}
	
}
