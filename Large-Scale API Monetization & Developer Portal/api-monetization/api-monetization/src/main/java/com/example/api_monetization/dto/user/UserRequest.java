package com.example.api_monetization.dto.user;

import java.util.Set;

import com.example.api_monetization.enums.AccountStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
public class UserRequest {

    @NotBlank(message = "First Name is required")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255)
    private String password;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number should contain 10 digits"
    )
    private String phone;

    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Builder.Default
    private Boolean emailVerified = false;

    @Builder.Default
    private Boolean mfaEnabled = false;

    @NotEmpty(message = "At least one role must be selected")
    private Set<Long> roleIds;
}