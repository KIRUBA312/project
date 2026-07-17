package com.example.api_monetization.dto.developer;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
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
public class ApiKeyRequest {

    @NotNull(message = "Application Id is required")
    private Long applicationId;

    private LocalDateTime expiresAt;

}