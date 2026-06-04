package com.example.airbnbbooking.service;

import java.util.List;


import com.example.airbnbbooking.dto.BookingRequestDto;
import com.example.airbnbbooking.dto.BookingResponseDto;

public interface BookingService {

	BookingResponseDto createBooking(BookingRequestDto dto);

	
	List<BookingResponseDto> getAllBookings();


	BookingResponseDto getBookingById(Long id);


	List<BookingResponseDto> getBookingsByGuest(Long guestId);


	List<BookingResponseDto> getBookingsByProperty(Long propertyId);


	BookingResponseDto cancelBooking(Long id);


	String deleteBooking(Long id);
	

}
