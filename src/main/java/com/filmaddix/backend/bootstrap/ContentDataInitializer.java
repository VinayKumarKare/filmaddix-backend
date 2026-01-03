package com.filmaddix.backend.bootstrap;

import com.filmaddix.backend.domain.enums.ContentType;
import com.filmaddix.backend.domain.enums.ReleaseType;
import com.filmaddix.backend.entity.*;
import com.filmaddix.backend.repository.ContentRepository;
import com.filmaddix.backend.repository.GenreRepository;
import com.filmaddix.backend.repository.LanguageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class ContentDataInitializer {

    private final ContentRepository contentRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;

    public ContentDataInitializer(
            ContentRepository contentRepository,
            LanguageRepository languageRepository,
            GenreRepository genreRepository
    ) {
        this.contentRepository = contentRepository;
        this.languageRepository = languageRepository;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void init() {

        // ✅ SAFE: Do not reinsert on every restart
        if (contentRepository.count() > 0) {
            return;
        }

        /* =========================
           MOVIE – Inception
           ========================= */

        Content movie = new Content();
        movie.setTitle("Inception");
        movie.setDescription(
                "A thief who steals corporate secrets through dream-sharing technology."
        );
        movie.setContentType(ContentType.MOVIE);
        movie.setPosterUrl("https://filmaddix.com/posters/inception.jpg");

        // ---------- Languages (already bootstrapped elsewhere)
        Language english = languageRepository.findAll()
                .stream()
                .filter(l -> "EN".equalsIgnoreCase(l.getCode()))
                .findFirst()
                .orElse(null);

        Language hindi = languageRepository.findAll()
                .stream()
                .filter(l -> "HI".equalsIgnoreCase(l.getCode()))
                .findFirst()
                .orElse(null);

        if (english != null) {
            movie.getLanguages().add(new ContentLanguage(movie, english));
        }
        if (hindi != null) {
            movie.getLanguages().add(new ContentLanguage(movie, hindi));
        }

        // ---------- Genres
        Genre sciFi = genreRepository.findByNameIgnoreCase("Sci-Fi").orElse(null);
        Genre thriller = genreRepository.findByNameIgnoreCase("Thriller").orElse(null);

        if (sciFi != null && thriller != null) {
            movie.getGenres().addAll(Set.of(sciFi, thriller));
        }

        // ---------- Theatrical Release
        ContentRelease theatrical = new ContentRelease();
        theatrical.setContent(movie);
        theatrical.setReleaseType(ReleaseType.THEATRICAL.name());
        theatrical.setReleaseDate(LocalDate.of(2010, 7, 16));
        theatrical.setPlatform(null);

        movie.getReleases().add(theatrical);

        // ---------- Media Asset (CORRECT WAY)
        MediaAsset trailer = new MediaAsset(
                "TRAILER",
                "https://filmaddix.com/trailers/inception.mp4",
                movie
        );

        movie.getMediaAssets().add(trailer);

        // ---------- Persist
        contentRepository.save(movie);
    }
}
