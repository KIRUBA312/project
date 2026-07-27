package com.example.multiregion_resilience.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.NOT_FOUND,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(
            ResourceAlreadyExistsException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.CONFLICT,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOperation(
            InvalidOperationException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(RegionUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleRegionUnavailable(
            RegionUnavailableException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }


    @ExceptionHandler(FailoverException.class)
    public ResponseEntity<ErrorResponse> handleFailoverException(
            FailoverException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }


    @ExceptionHandler(IdempotencyException.class)
    public ResponseEntity<ErrorResponse> handleIdempotencyException(
            IdempotencyException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.CONFLICT,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(
            OptimisticLockException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.CONFLICT,
                exception.getErrorCode(),
                exception.getMessage(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(
            ObjectOptimisticLockingFailureException.class
    )
    public ResponseEntity<ErrorResponse> handleJpaOptimisticLock(
            ObjectOptimisticLockingFailureException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.CONFLICT,
                ErrorCode.OPTIMISTIC_LOCK_CONFLICT,
                "The resource was modified by another request. "
                        + "Please retry the operation.",
                request
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {

        String message = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField()
                                + ": "
                                + error.getDefaultMessage()
                )
                .collect(Collectors.joining(", "));


        ErrorResponse response = buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_ERROR,
                message,
                request
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.CONFLICT,
                ErrorCode.RESOURCE_ALREADY_EXISTS,
                "Database constraint violation occurred.",
                request
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred.",
                request
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }


    private ErrorResponse buildResponse(
            HttpStatus status,
            ErrorCode errorCode,
            String message,
            HttpServletRequest request
    ) {

        String requestId =
                request.getHeader("X-Request-ID");


        if (requestId == null || requestId.isBlank()) {
            requestId = "N/A";
        }


        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                errorCode.name(),
                message,
                request.getRequestURI(),
                requestId
        );
    }
}
