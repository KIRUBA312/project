package com.example.api_monetization.dto.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersionRequest {

    @NotNull
    private Long apiId;

    @NotBlank
    @Size(max = 30)
    private String versionName;

    @Size(max = 500)
    private String endpoint;

    @Size(max = 500)
    private String swaggerUrl;

    @Size(max = 500)
    private String openapiUrl;

    private String releaseNotes;

}