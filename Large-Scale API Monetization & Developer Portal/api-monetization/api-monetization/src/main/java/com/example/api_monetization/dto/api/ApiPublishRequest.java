package com.example.api_monetization.dto.api;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPublishRequest {

    @NotNull
    private Long apiId;

    @NotNull
    private Long requestedBy;

}