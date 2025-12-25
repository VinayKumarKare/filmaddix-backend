package com.filmaddix.backend.service;

import com.filmaddix.backend.dto.CreateMovieRequest;
import com.filmaddix.backend.dto.MovieDto;
import com.filmaddix.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final List<MovieDto> movies = new ArrayList<>();

    public MovieService() {
        movies.add(new MovieDto(1L, "Inception", "English", 8.8, "Netflix"));
        movies.add(new MovieDto(2L, "RRR", "Telugu", 8.9, "Netflix"));
    }

    public List<MovieDto> getAllMovies(String ott) {
        if (ott == null) return movies;

        return movies.stream()
                .filter(m -> m.getOttPlatform().equalsIgnoreCase(ott))
                .toList();
    }

    public MovieDto getMovieById(Long id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movie not found with id: " + id)
                );
    }

    // ⭐ NEW METHOD
    public MovieDto createMovie(CreateMovieRequest request) {

        Long newId = (long) (movies.size() + 1);

        MovieDto movie = new MovieDto(
                newId,
                request.getTitle(),
                request.getLanguage(),
                request.getRating(),
                request.getOttPlatform()
        );

        movies.add(movie);
        return movie;
    }
}
