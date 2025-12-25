package com.filmaddix.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateMovieRequest {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 10, message = "Rating cannot be more than 10")
    private double rating;

    @NotBlank(message = "OTT platform is mandatory")
    private String ottPlatform;

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public double getRating() {
        return rating;
    }

    public String getOttPlatform() {
        return ottPlatform;
    }
}
