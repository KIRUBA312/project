package com.example.airbnbbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.airbnbbooking.dto.BookingRequestDto;
import com.example.airbnbbooking.dto.BookingResponseDto;
import com.example.airbnbbooking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<BookingResponseDto> createBooking(
			@RequestBody BookingRequestDto dto){
		return ResponseEntity.ok(bookingService.createBooking(dto));
		
	}
	@GetMapping
	public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	@GetMapping("/{id}")
	public ResponseEntity<BookingResponseDto> getBookingById(
			@PathVariable Long id){
		return ResponseEntity.ok(bookingService.getBookingById(id));
	}
	@GetMapping("/guest/{guestId}")
	public ResponseEntity<List<BookingResponseDto>> getBookingsByGuest(
			@PathVariable Long guestId){
		return ResponseEntity.ok(bookingService
				.getBookingsByGuest(guestId));
	}
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<BookingResponseDto>>
	getBookingsByProperty(@PathVariable Long propertyId){
		return ResponseEntity.ok(bookingService
				.getBookingsByProperty(propertyId));
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<BookingResponseDto> cancelBooking(
			@PathVariable Long id){
		return ResponseEntity.ok(
				bookingService.cancelBooking(id));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBooking(
			@PathVariable Long id){
		return ResponseEntity.ok(bookingService.deleteBooking(id));
	}

}
