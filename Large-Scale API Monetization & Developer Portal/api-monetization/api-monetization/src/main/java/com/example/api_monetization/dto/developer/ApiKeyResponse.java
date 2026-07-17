package com.example.api_monetization.dto.developer;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApiKeyStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyResponse {

    private Long id;

    private Long applicationId;

    private String apiKey;

    private String apiSecret;

    private ApiKeyStatus status;

    private LocalDateTime generatedAt;

    private LocalDateTime expiresAt;

    private LocalDateTime regeneratedAt;

}