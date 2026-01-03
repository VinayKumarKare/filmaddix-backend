package com.filmaddix.backend.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    private ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    /* =========================
       Static Factory Methods
       ========================= */

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("SUCCESS", message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("ERROR", message, null);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>("ERROR", message, data);
    }


    /* =========================
       Getters
       ========================= */

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
