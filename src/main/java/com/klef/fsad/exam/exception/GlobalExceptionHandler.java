package com.klef.fsad.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.klef.fsad.exam.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 Validation Exception
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        String msg = ex.getBindingResult()
                       .getFieldError()
                       .getDefaultMessage();

        return new ResponseEntity<>(
                new ApiResponse<>("error", msg, null),
                HttpStatus.BAD_REQUEST
        );
    }

    // 🔴 Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {

        ApiResponse<?> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}