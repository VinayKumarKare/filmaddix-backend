package com.filmaddix.backend.dto;

public class MovieDto {

    private Long id;
    private String title;
    private String language;
    private double rating;
    private String ottPlatform;

    public MovieDto(Long id, String title, String language, double rating, String ottPlatform) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.rating = rating;
        this.ottPlatform = ottPlatform;
    }

    public Long getId() {
        return id;
    }

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
