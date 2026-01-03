package com.filmaddix.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "content_languages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"content_id", "language_id"})
        }
)
public class ContentLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---- Relations ----

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    // ---- Optional metadata (future safe) ----
    // PRIMARY / DUBBED / SUBTITLE etc (later)
    // private String languageType;

    protected ContentLanguage() {}

    public ContentLanguage(Content content, Language language) {
        this.content = content;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public Content getContent() {
        return content;
    }

    public Language getLanguage() {
        return language;
    }
}
