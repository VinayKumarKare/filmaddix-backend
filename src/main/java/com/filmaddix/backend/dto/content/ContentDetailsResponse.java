package com.filmaddix.backend.dto.content;

import com.filmaddix.backend.domain.enums.ContentType;

import java.time.LocalDate;
import java.util.List;

public class ContentDetailsResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final ContentType contentType;
    private final String posterUrl;

    private final List<String> languages;
    private final List<String> genres;
    private final List<ReleaseDto> releases;
    private final List<MediaAssetDto> mediaAssets;

    public ContentDetailsResponse(
            Long id,
            String title,
            String description,
            ContentType contentType,
            String posterUrl,
            List<String> languages,
            List<String> genres,
            List<ReleaseDto> releases,
            List<MediaAssetDto> mediaAssets
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contentType = contentType;
        this.posterUrl = posterUrl;
        this.languages = languages;
        this.genres = genres;
        this.releases = releases;
        this.mediaAssets = mediaAssets;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<ReleaseDto> getReleases() {
        return releases;
    }

    public List<MediaAssetDto> getMediaAssets() {
        return mediaAssets;
    }

    /* =========================
       Nested DTOs
       ========================= */

    public static class ReleaseDto {

        private final String releaseType;   // THEATRICAL / OTT
        private final LocalDate releaseDate;
        private final String platform;
        private final String watchUrl;

        public ReleaseDto(
                String releaseType,
                LocalDate releaseDate,
                String platform,
                String watchUrl
        ) {
            this.releaseType = releaseType;
            this.releaseDate = releaseDate;
            this.platform = platform;
            this.watchUrl = watchUrl;
        }

        public String getReleaseType() {
            return releaseType;
        }

        public LocalDate getReleaseDate() {
            return releaseDate;
        }

        public String getPlatform() {
            return platform;
        }

        public String getWatchUrl() {
            return watchUrl;
        }
    }

    public static class MediaAssetDto {

        private final String type; // POSTER / TRAILER / BANNER
        private final String url;

        public MediaAssetDto(String type, String url) {
            this.type = type;
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
