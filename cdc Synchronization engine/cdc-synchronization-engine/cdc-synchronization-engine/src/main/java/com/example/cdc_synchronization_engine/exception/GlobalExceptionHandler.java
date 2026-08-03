package com.example.cdc_synchronization_engine.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiErrorResponse buildResponse(
            HttpStatus status,
            ErrorCode errorCode,
            String message,
            HttpServletRequest request) {

        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorCode(errorCode.name())
                .message(message)
                .path(request.getRequestURI())
                .requestId(request.getHeader("X-Correlation-ID") != null
                        ? request.getHeader("X-Correlation-ID")
                        : "N/A")
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(
                        HttpStatus.NOT_FOUND,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExists(
            ResourceAlreadyExistsException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildResponse(
                        HttpStatus.CONFLICT,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequest(
            InvalidRequestException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(
                        HttpStatus.BAD_REQUEST,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidOperation(
            InvalidOperationException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(
                        HttpStatus.BAD_REQUEST,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthentication(
            AuthenticationException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse(
                        HttpStatus.UNAUTHORIZED,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(
            UnauthorizedException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse(
                        HttpStatus.UNAUTHORIZED,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorResponse> handleForbidden(
            ForbiddenException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(buildResponse(
                        HttpStatus.FORBIDDEN,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(KafkaPublishException.class)
    public ResponseEntity<ApiErrorResponse> handleKafkaPublish(
            KafkaPublishException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(KafkaConsumeException.class)
    public ResponseEntity<ApiErrorResponse> handleKafkaConsume(
            KafkaConsumeException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(CDCProcessingException.class)
    public ResponseEntity<ApiErrorResponse> handleCDC(
            CDCProcessingException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(ElasticsearchSyncException.class)
    public ResponseEntity<ApiErrorResponse> handleElastic(
            ElasticsearchSyncException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(RetryLimitExceededException.class)
    public ResponseEntity<ApiErrorResponse> handleRetryLimit(
            RetryLimitExceededException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(
                        HttpStatus.BAD_REQUEST,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(IdempotencyException.class)
    public ResponseEntity<ApiErrorResponse> handleIdempotency(
            IdempotencyException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildResponse(
                        HttpStatus.CONFLICT,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(DeadLetterQueueException.class)
    public ResponseEntity<ApiErrorResponse> handleDLQ(
            DeadLetterQueueException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabase(
            DatabaseException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {

            errors.put(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errorCode", ErrorCode.INVALID_REQUEST.name());
        response.put("message", "Validation Failed");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(buildResponse(
                        HttpStatus.UNAUTHORIZED,
                        ErrorCode.AUTHENTICATION_FAILED,
                        "Invalid username or password",
                        request));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(buildResponse(
                        HttpStatus.FORBIDDEN,
                        ErrorCode.ACCESS_DENIED,
                        "Access Denied",
                        request));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleDataAccess(
            DataAccessException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCode.DATABASE_ERROR,
                        ex.getMostSpecificCause().getMessage(),
                        request));
    }

    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<ApiErrorResponse> handleKafka(
            KafkaException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCode.KAFKA_CONSUME_FAILED,
                        ex.getMessage(),
                        request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request));
    }
}