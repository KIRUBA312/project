package com.example.multiregion_resilience.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.multiregion_resilience.dto.CacheSyncRequest;
import com.example.multiregion_resilience.exception.ErrorCode;
import com.example.multiregion_resilience.exception.InvalidOperationException;
import com.example.multiregion_resilience.service.CacheService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CacheServiceImpl implements CacheService{

	private final RedisTemplate<String, Object> redisTemplate;

	public CacheServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void synchronizeCache(@Valid 
			CacheSyncRequest request) {
		// TODO Auto-generated method stub
		
		validateRequest(request);
		String cacheKey = request.getCacheKey().trim();
        String cacheValue = request.getCacheValue();
        String sourceRegion =
                request.getSourceRegion().trim()
                        .toUpperCase();
        String formattedValue = sourceRegion + ":" 
                        + cacheValue;

        redisTemplate.opsForValue().set(
                        cacheKey,formattedValue);
    }


    private void validateRequest(
            CacheSyncRequest request
    ) {

        if (request == null) {
            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Cache sync request cannot be null"
            );
        }
        if (request.getCacheKey() == null
                || request.getCacheKey().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Cache key cannot be empty"
            );
        }
        if (request.getCacheValue() == null
                || request.getCacheValue().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Cache value cannot be empty"
            );
        }
        if (request.getSourceRegion() == null
                || request.getSourceRegion().isBlank()) {

            throw new InvalidOperationException(
                    ErrorCode.INVALID_OPERATION,
                    "Source region cannot be empty"
            );
        }
    }
	
	
}
