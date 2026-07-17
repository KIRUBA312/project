package com.example.api_monetization.dto.api;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDocumentationRequest {

    @NotNull
    private Long apiId;

    private String overview;

    private String authentication;

    private String requestExample;

    private String responseExample;

    private String errorCodes;

}