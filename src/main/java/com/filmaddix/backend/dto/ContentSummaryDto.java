package com.filmaddix.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class ContentSummaryDto {

    private Long id;
    private String title;
    private String type; // MOVIE / SERIES
    private List<String> languages;

    private PrimaryReleaseDto primaryRelease;
    private String posterUrl;

    public ContentSummaryDto(
            Long id,
            String title,
            String type,
            List<String> languages,
            PrimaryReleaseDto primaryRelease,
            String posterUrl
    ) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.languages = languages;
        this.primaryRelease = primaryRelease;
        this.posterUrl = posterUrl;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public List<String> getLanguages() { return languages; }
    public PrimaryReleaseDto getPrimaryRelease() { return primaryRelease; }
    public String getPosterUrl() { return posterUrl; }
}
