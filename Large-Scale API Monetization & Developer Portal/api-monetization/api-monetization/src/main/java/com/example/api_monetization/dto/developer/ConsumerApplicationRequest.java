package com.example.api_monetization.dto.developer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ConsumerApplicationRequest {

    @NotNull(message = "Developer Id is required")
    private Long developerId;

    @NotBlank(message = "Application Name is required")
    @Size(max = 200)
    private String applicationName;

    private String description;

    @Size(max = 500)
    private String redirectUrl;

    @Size(max = 500)
    private String callbackUrl;

}