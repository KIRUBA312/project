package com.example.api_monetization.dto.developer;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.AccountStatus;

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
public class ConsumerApplicationResponse {

    private Long id;

    private Long developerId;

    private String applicationName;

    private String description;

    private String redirectUrl;

    private String callbackUrl;

    private AccountStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}