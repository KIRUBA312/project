package com.example.enterprise_iam.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.enterprise_iam.dto.request.ForgotPasswordRequestDto;
import com.example.enterprise_iam.dto.request.LoginRequestDto;
import com.example.enterprise_iam.dto.request.RefreshTokenRequestDto;
import com.example.enterprise_iam.dto.request.ResetPasswordRequestDto;
import com.example.enterprise_iam.dto.request.UserRegistrationRequestDto;
import com.example.enterprise_iam.dto.response.ApiResponseDto;
import com.example.enterprise_iam.dto.response.JwtResponseDto;
import com.example.enterprise_iam.entity.PasswordResetToken;
import com.example.enterprise_iam.entity.RefreshToken;
import com.example.enterprise_iam.entity.Role;
import com.example.enterprise_iam.entity.User;
import com.example.enterprise_iam.entity.UserRole;
import com.example.enterprise_iam.entity.VerificationToken;
import com.example.enterprise_iam.exception.ResourceAlreadyExistsException;
import com.example.enterprise_iam.exception.ResourceNotFoundException;
import com.example.enterprise_iam.repository.PasswordResetTokenRepository;
import com.example.enterprise_iam.repository.RefreshTokenRepository;
import com.example.enterprise_iam.repository.RoleRepository;
import com.example.enterprise_iam.repository.UserRepository;
import com.example.enterprise_iam.repository.UserRoleRepository;
import com.example.enterprise_iam.repository.VerificationTokenRepository;
import com.example.enterprise_iam.service.AuthService;
import com.example.enterprise_iam.service.MailService;
import com.example.enterprise_iam.service.RedisService;
import com.example.enterprise_iam.service.UserSessionService;
import com.example.enterprise_iam.util.JwtUtil;
import com.example.enterprise_iam.util.MapperUtil;

import jakarta.validation.Valid;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private MapperUtil mapperUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MailService mailService;
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	private UserSessionService userSessionService;
	@Autowired
	private RedisService redisService;
	
	private String generateRandomToken() {
		return UUID.randomUUID().toString();
	}
	@Override
	public ApiResponseDto register(@Valid UserRegistrationRequestDto request) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new ResourceAlreadyExistsException(
					"Email already registered");
		}
		User user = mapperUtil.toUser(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(false);
		user.setEmailVerified(false);
		user.setAccountNonLocked(true);
		user.setFailedAttempts(0);
		user.setMfaEnabled(false);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user = userRepository.save(user);
		Role role = roleRepository.findByName("ROLE_USER")
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Default Role ROLE_USER not found"));
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleRepository.save(userRole);
		
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setUser(user);
		verificationToken.setToken(generateRandomToken());
		verificationToken.setExpiryDate(
				LocalDateTime.now().plusHours(24));
		verificationTokenRepository.save(verificationToken);
		mailService.sendVerificationEmail(user.getEmail(), 
				verificationToken.getToken());
		
		
		return new ApiResponseDto(
				true,"Registration Successful.\n"
				+"Verification link printed in console");
	}

	@Override
	public JwtResponseDto login(@Valid LoginRequestDto request) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), 
						request.getPassword()));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		User user = userRepository.findByEmail(userDetails.getUsername())
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found"));
		if(!Boolean.TRUE.equals(user.getEnabled())) {
			throw new RuntimeException("Email not verified");
		}
		
		String accessToken = jwtUtil.generateAccessToken(userDetails);
		String refreshToken = jwtUtil.generateRefreshToken(userDetails);
		
		RefreshToken token = new RefreshToken();
		token.setUser(user);
		token.setToken(refreshToken);
		token.setRevoked(false);
		token.setExpiryDate(LocalDateTime.now().plusDays(7));
		
		refreshTokenRepository.save(token);
		
		userSessionService.createSession(user, accessToken
				, "WEB", "127.0.01");
		
		JwtResponseDto response = new JwtResponseDto();
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken);
		response.setExpiresIn(900000L);
		
		redisService.saveAccessToken(user.getEmail(), accessToken);
		redisService.saveRefreshToken(user.getEmail(), refreshToken);
		
		return response;
	}

	@Override
	public JwtResponseDto refreshToken(@Valid 
			RefreshTokenRequestDto request) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = refreshTokenRepository
				.findByToken(request.getRefreshToken())
				.orElseThrow(() ->
				new ResourceNotFoundException("Refresh Token not found"));
		if(Boolean.TRUE.equals(refreshToken.getRevoked())) {
			throw new RuntimeException("Refresh Token revoked");
		}
		if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Refresh Token expired");
		}
		User user = refreshToken.getUser();
		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities("ROLE_USER").build();
		
		String accessToken = jwtUtil.generateAccessToken(userDetails);
		
		JwtResponseDto response = new JwtResponseDto();
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken.getToken());
		response.setExpiresIn(900000L);
		redisService.saveAccessToken(user.getEmail(), accessToken);
		
		return response;
	}

	@Override
	public ApiResponseDto logout(String token) {
		// TODO Auto-generated method stub
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
				.orElseThrow(() ->
				new ResourceNotFoundException("Refresh Token not found"));
		User user = refreshToken.getUser();
		refreshToken.setRevoked(true);
		refreshTokenRepository.save(refreshToken);
		userSessionService.logoutSession(token);
		redisService.clearUserSession(user.getEmail());
		
		return new ApiResponseDto(true,
				"Logout Successful");
	}

	@Override
	public ApiResponseDto verifyEmail(String token) {
		// TODO Auto-generated method stub
		VerificationToken verificationToken = verificationTokenRepository
				.findByToken(token)
				.orElseThrow(() ->
				new ResourceNotFoundException("Invalid verification token"));
		if(verificationToken.getExpiryDate().isBefore(
				LocalDateTime.now())) {
			throw new RuntimeException("Verification token expired");
		}
		
		User user = verificationToken.getUser();
		user.setEnabled(true);
		user.setEmailVerified(true);
		
		userRepository.save(user);
		verificationTokenRepository.delete(verificationToken);
		return new ApiResponseDto(true,
				"Email verified successfully");
	}

	@Override
	public ApiResponseDto forgotPassword(@Valid ForgotPasswordRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found"));
		PasswordResetToken token = new PasswordResetToken();
		
		token.setUser(user);
		token.setToken(generateRandomToken());
		token.setUsed(false);
		token.setExpiryDate(LocalDateTime.now().plusHours(1));
		
		passwordResetTokenRepository.save(token);
		mailService.sendPasswordResetEmail(user.getEmail(),
				token.getToken());
		
		return new ApiResponseDto(true,"Password "
				+ "reset limk generated. Check console");
	}

	@Override
	public ApiResponseDto resetPassword(@Valid ResetPasswordRequestDto request) {
		// TODO Auto-generated method stub
		PasswordResetToken resetToken = passwordResetTokenRepository
				.findByToken(request.getToken()).orElseThrow(() ->
				new ResourceNotFoundException("Invalid reset token"));
		if(Boolean.TRUE.equals(resetToken.getUsed())) {
			throw new RuntimeException("Token already used");
		}
		if(resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Reset token expired");
		}
		User user = resetToken.getUser();
		
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepository.save(user);
		resetToken.setUsed(true);
		passwordResetTokenRepository.save(resetToken);
		return new ApiResponseDto(true,
				"Password reset successful");
	}

}
