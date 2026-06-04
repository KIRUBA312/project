package com.example.airbnbbooking.service.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airbnbbooking.dto.BookingRequestDto;
import com.example.airbnbbooking.dto.BookingResponseDto;
import com.example.airbnbbooking.entity.Booking;
import com.example.airbnbbooking.entity.Property;
import com.example.airbnbbooking.entity.User;
import com.example.airbnbbooking.enums.BookingStatus;
import com.example.airbnbbooking.exception.BookingConflictException;
import com.example.airbnbbooking.exception.InvalidBookingException;
import com.example.airbnbbooking.exception.ResourceNotFoundException;
import com.example.airbnbbooking.repository.BookingRepository;
import com.example.airbnbbooking.repository.PropertyRepository;
import com.example.airbnbbooking.repository.UserRepository;
import com.example.airbnbbooking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public BookingResponseDto createBooking(BookingRequestDto dto) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(dto.getPropertyId())
				.orElseThrow(() -> new ResourceNotFoundException("Property not found"));
		User guest = userRepository.findById(dto.getGuestId())
				.orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
		if(dto.getStartDate().isAfter(dto.getEndDate())) {
			throw new InvalidBookingException("Start date cannot be after end date");
		}
		List<Booking> conflicts = bookingRepository.findConflictingBookings(
				dto.getPropertyId(), dto.getStartDate(), dto.getEndDate());
		if (!conflicts.isEmpty()) {
			throw new BookingConflictException(
					"Property already booked for selected dates");
			
		}
		long nights = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
		BigDecimal totalPrice = property.getPricePerNight()
				.multiply(BigDecimal.valueOf(nights));
		Booking booking = new Booking();
		
		booking.setProperty(property);
		booking.setGuest(guest);
		booking.setStartDate(dto.getStartDate());
		booking.setEndDate(dto.getEndDate());
		booking.setTotalPrice(totalPrice);
		booking.setStatus(BookingStatus.CONFIRMED);
		
		booking = bookingRepository.save(booking);
		
		return maptoresponse(booking);
	}
	@Override
	public List<BookingResponseDto> getAllBookings() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public BookingResponseDto getBookingById(Long id) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
		return maptoresponse(booking);
	}
	@Override
	public List<BookingResponseDto> getBookingsByGuest(Long guestId) {
		// TODO Auto-generated method stub
		return bookingRepository.findByGuestId(guestId).stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<BookingResponseDto> getBookingsByProperty(
			Long propertyId) {
		// TODO Auto-generated method stub
		return bookingRepository.findByPropertyId(propertyId)
				.stream().map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public BookingResponseDto cancelBooking(Long id) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
		booking.setStatus(BookingStatus.CANCELLED);
		booking = bookingRepository.save(booking);
		return maptoresponse(booking);
	}
	@Override
	public String deleteBooking(Long id) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Booking not found"));
		bookingRepository.delete(booking);
		return "Booking deleted successfully";
	}
	private BookingResponseDto maptoresponse(Booking booking) {
		BookingResponseDto dto = new BookingResponseDto();
		dto.setId(booking.getId());
		dto.setPropertyId(booking.getProperty().getId());
		dto.setGuestId(booking.getGuest().getId());
		dto.setGuestName(booking.getGuest().getName());
		dto.setStartDate(booking.getStartDate());
		dto.setEndDate(booking.getEndDate());
		dto.setTotalPrice(booking.getTotalPrice());
		dto.setStatus(booking.getStatus().name());
		
		return dto;
	}
	
	
}
