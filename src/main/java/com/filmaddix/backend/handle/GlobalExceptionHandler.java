package com.filmaddix.backend.handle;

import com.filmaddix.backend.dto.ApiResponse;
import com.filmaddix.backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ApiResponse response = new ApiResponse(
                "ERROR",
                ex.getMessage(),
                "RESOURCE_NOT_FOUND"
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {

        ApiResponse response = new ApiResponse(
                "ERROR",
                "Internal server error",
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        ApiResponse response = new ApiResponse(
                "ERROR",
                errorMessage,
                "VALIDATION_ERROR"
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
