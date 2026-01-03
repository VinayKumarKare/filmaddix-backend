package com.filmaddix.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "content_releases",
        indexes = {
                @Index(name = "idx_release_type", columnList = "release_type"),
                @Index(name = "idx_release_date", columnList = "release_date")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Parent content (Movie / Web Series)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "content_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_content_release_content")
    )
    private Content content;

    /**
     * THEATRICAL | OTT | FUTURE
     */
    @Column(name = "release_type", nullable = false, length = 30)
    private String releaseType;

    /**
     * Release date (nullable for announced/future)
     */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * Platform name
     * Examples: Netflix, Amazon Prime, Disney+ Hotstar
     * Nullable for theatrical
     */
    @Column(length = 100)
    private String platform;

    /**
     * Direct link to watch (optional)
     */
    @Column(length = 500)
    private String watchUrl;
}
