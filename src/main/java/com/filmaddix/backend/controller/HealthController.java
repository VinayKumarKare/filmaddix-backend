package com.filmaddix.backend.controller;

import com.filmaddix.backend.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success(
                "UP",
                "FilmAddix backend is running"
        );
    }
}
