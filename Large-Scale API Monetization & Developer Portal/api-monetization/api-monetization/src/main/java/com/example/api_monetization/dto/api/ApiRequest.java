package com.example.api_monetization.dto.api;

import com.example.api_monetization.enums.ApiLifecycleStatus;
import com.example.api_monetization.enums.ApiVisibility;
import com.example.api_monetization.enums.AuthenticationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {

    private Long categoryId;

    @NotNull(message = "Publisher Id is required")
    private Long publisherId;

    @NotBlank
    @Size(max = 200)
    private String apiName;

    @Size(max = 255)
    private String displayName;

    private String description;

    @NotBlank
    @Size(max = 500)
    private String baseUrl;

    @Size(max = 30)
    private String currentVersion;

    @Builder.Default
    private ApiVisibility visibility = ApiVisibility.PUBLIC;

    @Builder.Default
    private ApiLifecycleStatus lifecycleStatus = ApiLifecycleStatus.DRAFT;

    @Builder.Default
    private AuthenticationType authenticationType = AuthenticationType.API_KEY;

    @Builder.Default
    private Integer rateLimitPerMinute = 100;

}