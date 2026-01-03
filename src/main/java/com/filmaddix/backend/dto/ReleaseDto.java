package com.filmaddix.backend.dto;

import java.time.LocalDate;

public class ReleaseDto {

    private String type; // THEATRICAL / OTT
    private String platform;
    private LocalDate releaseDate;

    public ReleaseDto(String type, String platform, LocalDate releaseDate) {
        this.type = type;
        this.platform = platform;
        this.releaseDate = releaseDate;
    }

    public String getType() { return type; }
    public String getPlatform() { return platform; }
    public LocalDate getReleaseDate() { return releaseDate; }
}
