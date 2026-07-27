package com.example.multiregion_resilience.service;

import com.example.multiregion_resilience.dto.FailoverRequest;
import com.example.multiregion_resilience.dto.FailoverResponse;
import com.example.multiregion_resilience.dto.PageResponse;

import jakarta.validation.Valid;

public interface FailoverService {

	FailoverResponse performFailover(
			@Valid FailoverRequest request,
			String idempotencyKey);

	FailoverResponse performFailback(
			@Valid FailoverRequest request,
			String idempotencyKey);

	FailoverResponse getFailoverById(Long id);
	
	PageResponse<FailoverResponse> getAllFailovers(
			int page,int size);

	PageResponse<FailoverResponse> 
	getFailoversBySourceRegion(String regionCode, 
			int page, int size);

	PageResponse<FailoverResponse> getFailoversByTargetRegion(
			String regionCode, int page, int size);

}
