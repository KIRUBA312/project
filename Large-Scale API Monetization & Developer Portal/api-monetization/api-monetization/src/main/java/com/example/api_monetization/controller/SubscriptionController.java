package com.example.api_monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.subscription.DeveloperSubscriptionRequest;
import com.example.api_monetization.dto.subscription.DeveloperSubscriptionResponse;
import com.example.api_monetization.dto.subscription.SubscriptionHistoryResponse;
import com.example.api_monetization.dto.subscription.SubscriptionPlanRequest;
import com.example.api_monetization.dto.subscription.SubscriptionPlanResponse;
import com.example.api_monetization.service.SubscriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	 @PostMapping("/plans")
	    public ResponseEntity<SubscriptionPlanResponse> createPlan(
	            @Valid @RequestBody SubscriptionPlanRequest request) {

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(subscriptionService.createPlan(request));
	    }

	    @PutMapping("/plans/{planId}")
	    public ResponseEntity<SubscriptionPlanResponse> updatePlan(
	            @PathVariable Long planId,
	            @Valid @RequestBody SubscriptionPlanRequest request) {

	        return ResponseEntity.ok(
	                subscriptionService.updatePlan(planId, request));
	    }

	    @GetMapping("/plans/{planId}")
	    public ResponseEntity<SubscriptionPlanResponse> getPlan(
	            @PathVariable Long planId) {

	        return ResponseEntity.ok(
	                subscriptionService.getPlan(planId));
	    }

	    @GetMapping("/plans")
	    public ResponseEntity<List<SubscriptionPlanResponse>> getPlans() {

	        return ResponseEntity.ok(
	                subscriptionService.getPlans());
	    }
	    
	    @PostMapping
	    public ResponseEntity<DeveloperSubscriptionResponse> subscribe(
	            @Valid @RequestBody DeveloperSubscriptionRequest request) {

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(subscriptionService.subscribe(request));
	    }

	    @PutMapping("/{subscriptionId}")
	    public ResponseEntity<DeveloperSubscriptionResponse> updateSubscription(
	            @PathVariable Long subscriptionId,
	            @Valid @RequestBody DeveloperSubscriptionRequest request) {

	        return ResponseEntity.ok(
	                subscriptionService.updateSubscription(
	                        subscriptionId,
	                        request));
	    }

	    @GetMapping("/{subscriptionId}")
	    public ResponseEntity<DeveloperSubscriptionResponse> getSubscription(
	            @PathVariable Long subscriptionId) {

	        return ResponseEntity.ok(
	                subscriptionService.getSubscription(subscriptionId));
	    }

	    @GetMapping("/developer/{developerId}")
	    public ResponseEntity<List<DeveloperSubscriptionResponse>>
	            getDeveloperSubscriptions(
	                    @PathVariable Long developerId) {

	        return ResponseEntity.ok(
	                subscriptionService.getDeveloperSubscriptions(
	                        developerId));
	    }
	    @GetMapping("/{subscriptionId}/history")
	    public ResponseEntity<List<SubscriptionHistoryResponse>>
	    getSubscriptionHistory(@PathVariable Long subscriptionId){
	    	return ResponseEntity.ok(
	    			subscriptionService.getHistory(subscriptionId));
	    }
}
