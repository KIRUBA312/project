package com.example.multiregion_resilience.service;

import com.example.multiregion_resilience.dto.CacheSyncRequest;

import jakarta.validation.Valid;

public interface CacheService {

	void synchronizeCache(@Valid CacheSyncRequest request);

}
