package com.filmaddix.backend.controller;

import com.filmaddix.backend.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ApiResponse health() {
        return new ApiResponse(
                "SUCCESS",
                "FilmAddix backend is running"
        );
    }
}
