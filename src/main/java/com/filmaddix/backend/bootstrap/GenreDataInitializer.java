package com.filmaddix.backend.bootstrap;

import com.filmaddix.backend.entity.Genre;
import com.filmaddix.backend.repository.GenreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreDataInitializer implements ApplicationRunner {

    private final GenreRepository genreRepository;

    public GenreDataInitializer(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<String> masterGenres = List.of(
                "Action",
                "Drama",
                "Comedy",
                "Thriller",
                "Romance",
                "Horror",
                "Sci-Fi",
                "Documentary"
        );

        for (String genreName : masterGenres) {
            if (!genreRepository.existsByNameIgnoreCase(genreName)) {
                genreRepository.save(new Genre(genreName));
            }
        }

        System.out.println("✅ Genre master data ensured");
    }
}
