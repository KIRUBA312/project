package com.example.api_monetization.dto.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(max = 150)
    private String categoryName;

    private String description;

}