package com.filmaddix.backend.dto;

import java.time.LocalDate;

public class PrimaryReleaseDto {

    private String releaseType;   // THEATRICAL / OTT / DIGITAL
    private LocalDate releaseDate;

    public PrimaryReleaseDto(String releaseType, LocalDate releaseDate) {
        this.releaseType = releaseType;
        this.releaseDate = releaseDate;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
