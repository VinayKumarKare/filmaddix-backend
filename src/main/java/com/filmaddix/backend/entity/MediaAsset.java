package com.filmaddix.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media_assets")
public class MediaAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // POSTER / TRAILER / LINK
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    protected MediaAsset() {
        // JPA only
    }

    public MediaAsset(String type, String url, Content content) {
        this.type = type;
        this.url = url;
        this.content = content;
    }

    public String getType() { return type; }
    public String getUrl() { return url; }
}
