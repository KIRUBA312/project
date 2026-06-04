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

import com.example.airbnbbooking.dto.ReviewRequestDto;
import com.example.airbnbbooking.dto.ReviewResponseDto;
import com.example.airbnbbooking.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ReviewResponseDto> createReview(
			@RequestBody ReviewRequestDto dto){
		return ResponseEntity.ok(reviewService.createReview(dto));
	}
	@GetMapping
	public ResponseEntity<List<ReviewResponseDto>> getAllReviews(){
		return ResponseEntity.ok(reviewService.getAllReviews());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ReviewResponseDto> getReviewById(
			@PathVariable Long id){
		return ResponseEntity.ok(reviewService.getReviewsById(id));
	}
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<ReviewResponseDto>>
	getReviewsByProperty(@PathVariable Long propertyId){
		return ResponseEntity.ok(reviewService
				.getReviewsByPropertyId(propertyId));
		
	}
	@GetMapping("/guest/{guestId}")
	public ResponseEntity<List<ReviewResponseDto>> getReviewsByGuest(
			@PathVariable Long guestId){
		return ResponseEntity.ok(reviewService.getReviewsByGuest(guestId));
	}
	@PutMapping("/{id}")
	public ResponseEntity<ReviewResponseDto> updateReview(
			@PathVariable Long id,@RequestBody ReviewRequestDto dto)
	{
		return ResponseEntity.ok(reviewService.updateReview(id, dto));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id){
		return ResponseEntity.ok(reviewService.deleteReview(id));
	}
	
}
