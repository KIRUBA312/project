package com.example.api_monetization.dto.api;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApiLifecycleStatus;
import com.example.api_monetization.enums.ApiVisibility;
import com.example.api_monetization.enums.AuthenticationType;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Long id;

    private Long categoryId;

    private Long publisherId;

    private String apiName;

    private String displayName;

    private String description;

    private String baseUrl;

    private String currentVersion;

    private ApiVisibility visibility;

    private ApiLifecycleStatus lifecycleStatus;

    private AuthenticationType authenticationType;

    private Integer rateLimitPerMinute;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}