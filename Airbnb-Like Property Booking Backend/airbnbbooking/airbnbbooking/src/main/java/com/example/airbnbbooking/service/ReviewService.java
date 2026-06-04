package com.example.airbnbbooking.service;

import java.util.List;


import com.example.airbnbbooking.dto.ReviewRequestDto;
import com.example.airbnbbooking.dto.ReviewResponseDto;

public interface ReviewService {

	ReviewResponseDto createReview(ReviewRequestDto dto);

	List<ReviewResponseDto> getAllReviews();

	ReviewResponseDto getReviewsById(Long id);

	List<ReviewResponseDto> getReviewsByPropertyId(Long propertyId);

	List<ReviewResponseDto> getReviewsByGuest(Long guestId);

	ReviewResponseDto updateReview(Long id, ReviewRequestDto dto);

	String deleteReview(Long id);

}
