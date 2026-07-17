package com.example.api_monetization.dto.developer;

import java.time.LocalDateTime;

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
public class DeveloperResponse {

    private Long id;

    private Long userId;

    private String companyName;

    private String website;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}