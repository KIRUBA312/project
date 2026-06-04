package com.example.airbnbbooking.util;

public class AppConstants {

	private AppConstants() {}
	
	public static final int DEFAULT_PAGE_NUMBER=0;
	public static final int DEFAULT_PAGE_SIZE=10;
	
	public static final String DEFAULT_SORT_BY = "id";
	public static final String DEFAULT_SORT_DIRECTION = "asc";
	
	public static final String BOOKING_REQUESTED = "REQUESTED";
	public static final String BOOKING_CONFIRMED = "CONFIRMED";
	public static final String BOOKING_CANCELLED = "CANCELLED";
	public static final String BOOKING_COMPLETED = "COMPLETED";
	
	public static final String ROLE_HOST = "HOST";
	public static final String ROLE_GUEST = "GUEST";
	
	public static final String USER_CREATED = 
			"User created successfully";
	public static final String USER_UPDATED =
			"User updated successfully";
	public static final String USER_DELETED =
			"User deleted successfully";
	public static final String PROPERTY_CREATED = 
			"Property created successfully";
	public static final String PROPERTY_UPDATED =
			"Property updated successfully";
	public static final String PROPERTY_DELETED =
			"Property deleted successfully";
	public static final String BOOKING_CREATED_MESSAGE =
			"Booking created successfully";
	public static final String BOOKING_CANCELLED_MESSAGE =
			"Booking cancelled successfully";
	public static final String REVIEW_CREATED =
			"Review submitted successfully";
	
	public static final String USER_NOT_FOUND = 
			"User not found ";
	public static final String PROPERTY_NOT_FOUND =
			"Property not found";
	public static final String BOOKING_NOT_FOUND =
			"Booking not found";
	public static final String REVIEW_NOT_FOUND =
			"Review not found";
	public static final String PROPERTY_NOT_AVAILABLE =
			"Property is not available for selected dates";
	public static final String INVALID_DATE_RANGE =
			"End date must be greater than start date";
	
	
	
	
}
