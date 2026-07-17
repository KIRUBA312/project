package com.example.api_monetization.dto.api;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApiKeyStatus;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersionResponse {

    private Long id;

    private Long apiId;

    private String versionName;

    private String endpoint;

    private String swaggerUrl;

    private String openapiUrl;

    private String releaseNotes;

    private ApiKeyStatus status;

    private LocalDateTime publishedDate;

    private LocalDateTime deprecatedDate;

    private LocalDateTime retiredDate;

    private LocalDateTime createdAt;

}