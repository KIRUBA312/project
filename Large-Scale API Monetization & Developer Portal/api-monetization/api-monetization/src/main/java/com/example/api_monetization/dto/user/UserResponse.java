package com.example.api_monetization.dto.user;

import java.time.LocalDateTime;
import java.util.Set;

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
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private AccountStatus accountStatus;

    private Boolean emailVerified;

    private Boolean mfaEnabled;

    private Integer failedLoginAttempts;

    private Boolean accountLocked;

    private LocalDateTime lockTime;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Set<RoleResponse> roles;
}