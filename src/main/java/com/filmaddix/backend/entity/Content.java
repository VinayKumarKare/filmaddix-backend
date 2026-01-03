package com.filmaddix.backend.entity;

import com.filmaddix.backend.domain.enums.ContentType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* =========================
       Relationships (EMPTY for now)
       ========================= */

    @OneToMany(
            mappedBy = "content",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<ContentLanguage> languages = new HashSet<>();



    @OneToMany(
            mappedBy = "content",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<ContentRelease> releases = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "content_genres",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"),
            uniqueConstraints = {
                    @UniqueConstraint(
                            name = "uk_content_genre",
                            columnNames = {"content_id", "genre_id"}
                    )
            }
    )
    private Set<Genre> genres = new HashSet<>();



    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private Set<MediaAsset> mediaAssets = new HashSet<>();

    /* =========================
       Lifecycle hooks
       ========================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /* =========================
       Getters & Setters
       ========================= */

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Set<ContentLanguage> getLanguages() {
        return languages;
    }

    public Set<ContentRelease> getReleases() {
        return releases;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<MediaAsset> getMediaAssets() {
        return mediaAssets;
    }
}
