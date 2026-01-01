package com.filmaddix.backend.service;

import com.filmaddix.backend.dto.CreateMovieRequest;
import com.filmaddix.backend.dto.MovieDto;
import com.filmaddix.backend.domain.Movie;
import com.filmaddix.backend.exception.ResourceNotFoundException;
import com.filmaddix.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // GET /movies
    public List<MovieDto> getAllMovies(String ott) {

        List<Movie> movies = (ott == null)
                ? movieRepository.findAll()
                : movieRepository.findByOttPlatformIgnoreCase(ott);

        return movies.stream()
                .map(this::toDto)
                .toList();
    }

    // GET /movies/{id}
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movie not found with id: " + id)
                );

        return toDto(movie);
    }

    // POST /movies
    public MovieDto createMovie(CreateMovieRequest request) {

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setLanguage(request.getLanguage());
        movie.setRating(request.getRating());
        movie.setOttPlatform(request.getOttPlatform());

        Movie saved = movieRepository.save(movie);

        return toDto(saved);
    }

    // 🔁 Entity → DTO mapping
    private MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getLanguage(),
                movie.getRating(),
                movie.getOttPlatform()
        );
    }
}
