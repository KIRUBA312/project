package com.example.enterprise_iam.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(
			ResourceNotFoundException ex,
			HttpServletRequest request){
		
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyExists(
			ResourceAlreadyExistsException ex,
			HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentials(
			InvalidCredentialsException ex,HttpServletRequest request){
		
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				ex.getMessage(),request.getRequestURI());
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handleInvalidToken(
			InvalidTokenException ex,HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<ErrorResponse> handleTokenExpired(
			TokenExpiredException ex,HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(AccountLockedException.class)
	public ResponseEntity<ErrorResponse> handleAccountLocked(
			AccountLockedException ex,HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.LOCKED.value(),
				HttpStatus.LOCKED.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.LOCKED);
	}
	@ExceptionHandler(EmailNotVerifiedException.class)
	public ResponseEntity<ErrorResponse> handleEmailNotVerified(
			EmailNotVerifiedException ex,HttpServletRequest request){
		
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ErrorResponse> handlePasswordMismatch(
			PasswordMismatchException ex,HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorized(
			UnauthorizedException ex,HttpServletRequest request){
		
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbidden(
			ForbiddenException ex,HttpServletRequest request){
		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ==========================================
    // Validation Errors
    // ==========================================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {

            String field = ((FieldError) error).getField();

            String message = error.getDefaultMessage();

            errors.put(field, message);

        });

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());

        response.put("status", HttpStatus.BAD_REQUEST.value());

        response.put("path", request.getRequestURI());

        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ==========================================
    // All Other Exceptions
    // ==========================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
