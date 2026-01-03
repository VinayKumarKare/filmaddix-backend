package com.filmaddix.backend.dto;

import java.util.List;

public class ContentDetailDto {

    private Long id;
    private String title;
    private String type;

    private List<String> languages;
    private List<String> genres;
    private List<ReleaseDto> releases;

    private RatingDto rating;
    private MediaAssetsDto media;

    public ContentDetailDto(
            Long id,
            String title,
            String type,
            List<String> languages,
            List<String> genres,
            List<ReleaseDto> releases,
            RatingDto rating,
            MediaAssetsDto media
    ) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.languages = languages;
        this.genres = genres;
        this.releases = releases;
        this.rating = rating;
        this.media = media;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public List<String> getLanguages() { return languages; }
    public List<String> getGenres() { return genres; }
    public List<ReleaseDto> getReleases() { return releases; }
    public RatingDto getRating() { return rating; }
    public MediaAssetsDto getMedia() { return media; }
}
