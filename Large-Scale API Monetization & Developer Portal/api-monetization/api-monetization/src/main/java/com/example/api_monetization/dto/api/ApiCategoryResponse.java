package com.example.api_monetization.dto.api;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiCategoryResponse {

    private Long id;

    private String categoryName;

    private String description;

    private LocalDateTime createdAt;

}