package com.example.api_monetization.dto.auth;

import java.util.List;

import com.example.api_monetization.dto.user.UserResponse;
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
public class LoginResponse {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String accessToken;

    private String refreshToken;
    
    private UserResponse user;

    @Builder.Default
    private String tokenType = "Bearer";

    private List<String> roles;

    private AccountStatus accountStatus;

    private boolean emailVerified;

    private boolean mfaEnabled;
    
}