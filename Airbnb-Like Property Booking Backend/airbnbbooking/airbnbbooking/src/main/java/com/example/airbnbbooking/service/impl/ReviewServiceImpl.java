package com.example.airbnbbooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airbnbbooking.dto.ReviewRequestDto;
import com.example.airbnbbooking.dto.ReviewResponseDto;
import com.example.airbnbbooking.entity.Property;
import com.example.airbnbbooking.entity.Review;
import com.example.airbnbbooking.entity.User;
import com.example.airbnbbooking.exception.ResourceNotFoundException;
import com.example.airbnbbooking.repository.PropertyRepository;
import com.example.airbnbbooking.repository.ReviewRepository;
import com.example.airbnbbooking.repository.UserRepository;
import com.example.airbnbbooking.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public ReviewResponseDto createReview(ReviewRequestDto dto) {
		// TODO Auto-generated method stub
		Property property = propertyRepository.findById(
				dto.getPropertyId()).orElseThrow(() ->
				new ResourceNotFoundException("Property not found"));
		User guest = userRepository.findById(dto.getGuestId())
				.orElseThrow(() ->
				new ResourceNotFoundException("Guest not found"));
		Review review = new Review();
		
		review.setProperty(property);
		review.setGuest(guest);
		review.setRating(dto.getRating());
		review.setComment(dto.getComment());
		review.setCreatedAt(LocalDateTime.now());
		
		review = reviewRepository.save(review);
		return maptoresponse(review);
	}
	@Override
	public List<ReviewResponseDto> getAllReviews() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll().stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public ReviewResponseDto getReviewsById(Long id) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Review not found"));
		return maptoresponse(review);
	}
	@Override
	public List<ReviewResponseDto> getReviewsByPropertyId(
			Long propertyId) {
		// TODO Auto-generated method stub
		return reviewRepository.findByPropertyId(propertyId)
				.stream().map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public List<ReviewResponseDto> getReviewsByGuest(Long guestId) {
		// TODO Auto-generated method stub
		return reviewRepository.findByGuestId(guestId).stream()
				.map(this::maptoresponse)
				.collect(Collectors.toList());
	}
	@Override
	public ReviewResponseDto updateReview(Long id, ReviewRequestDto dto) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Review not found"));
		
		review.setRating(dto.getRating());
		review.setComment(dto.getComment());
		
		review = reviewRepository.save(review);
		return maptoresponse(review);
	}
	@Override
	public String deleteReview(Long id) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Review not found"));
		reviewRepository.delete(review);
		return "Review deleted successfully";
	}
	
	private ReviewResponseDto maptoresponse(Review review) {
		ReviewResponseDto dto = new ReviewResponseDto();
		dto.setId(review.getId());
		dto.setPropertyId(review.getProperty().getId());
		dto.setPropertyTitle(review.getProperty().getTitle());
		dto.setGuestId(review.getGuest().getId());
		dto.setGuestName(review.getGuest().getName());
		dto.setRating(review.getRating());
		dto.setComment(review.getComment());
		dto.setCreatedAt(review.getCreatedAt());
		return dto;
	}
	
	
	
}
