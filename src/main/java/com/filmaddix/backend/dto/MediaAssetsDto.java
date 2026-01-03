package com.filmaddix.backend.dto;

public class MediaAssetsDto {

    private String posterUrl;
    private String trailerUrl;

    public MediaAssetsDto(String posterUrl, String trailerUrl) {
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
    }

    public String getPosterUrl() { return posterUrl; }
    public String getTrailerUrl() { return trailerUrl; }
}
