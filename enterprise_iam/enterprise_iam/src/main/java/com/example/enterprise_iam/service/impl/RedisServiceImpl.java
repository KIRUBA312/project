package com.example.enterprise_iam.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
    private static final String ACCESS_PREFIX = "ACCESS_";

    private static final String REFRESH_PREFIX = "REFRESH_";

    @Override
    public void saveAccessToken(String email, String token) {

        redisTemplate.opsForValue().set(
                ACCESS_PREFIX + email,
                token,
                15,
                TimeUnit.MINUTES);
    }

    @Override
    public void saveRefreshToken(String email, String token) {

        redisTemplate.opsForValue().set(
                REFRESH_PREFIX + email,
                token,
                7,
                TimeUnit.DAYS);
    }

    @Override
    public String getAccessToken(String email) {

        Object value = redisTemplate.opsForValue()
                .get(ACCESS_PREFIX + email);

        return value == null ? null : value.toString();
    }

    @Override
    public String getRefreshToken(String email) {

        Object value = redisTemplate.opsForValue()
                .get(REFRESH_PREFIX + email);

        return value == null ? null : value.toString();
    }

    @Override
    public void deleteAccessToken(String email) {

        redisTemplate.delete(ACCESS_PREFIX + email);
    }

    @Override
    public void deleteRefreshToken(String email) {

        redisTemplate.delete(REFRESH_PREFIX + email);
    }

    @Override
    public boolean hasSession(String email) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(ACCESS_PREFIX + email));
    }

    @Override
    public void clearUserSession(String email) {

        deleteAccessToken(email);
        deleteRefreshToken(email);
    }
	
}
