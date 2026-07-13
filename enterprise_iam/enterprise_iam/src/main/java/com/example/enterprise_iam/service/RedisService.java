package com.example.enterprise_iam.service;

public interface RedisService {

    void saveAccessToken(String email, String token);

    void saveRefreshToken(String email, String token);

    String getAccessToken(String email);

    String getRefreshToken(String email);

    void deleteAccessToken(String email);

    void deleteRefreshToken(String email);

    boolean hasSession(String email);

    void clearUserSession(String email);
}
