package com.example.api_monetization.dto.api;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDocumentationResponse {

    private Long id;

    private Long apiId;

    private String overview;

    private String authentication;

    private String requestExample;

    private String responseExample;

    private String errorCodes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}