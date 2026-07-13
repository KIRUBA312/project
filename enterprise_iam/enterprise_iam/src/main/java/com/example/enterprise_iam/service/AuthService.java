package com.example.enterprise_iam.service;

import com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto;
import com.example.enterprise_iam.dto.request.LoginRequestDto;
import com.example.enterprise_iam.dto.request.RefreshTokenRequestDto;
import com.example.enterprise_iam.dto.request.ResetPasswordRequestDto;
import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.JwtResponseDto;

import jakarta.validation.Valid;

public interface AuthService {

	ApiResponseDto register(@Valid UserRegistrationRequestDto request);

	JwtResponseDto login(@Valid LoginRequestDto request);

	JwtResponseDto refreshToken(@Valid RefreshTokenRequestDto request);

	ApiResponseDto logout(String token);

	ApiResponseDto verifyEmail(String token);

	ApiResponseDto forgotPassword(@Valid ForgotPasswordRequestDto request);

	ApiResponseDto resetPassword(@Valid ResetPasswordRequestDto request);

}
