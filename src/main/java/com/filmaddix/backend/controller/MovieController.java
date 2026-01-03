package com.filmaddix.backend.controller;

import com.filmaddix.backend.dto.ApiResponse;
import com.filmaddix.backend.dto.CreateMovieRequest;
import com.filmaddix.backend.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ApiResponse<?> getMovies(@RequestParam(required = false) String ott) {
        return ApiResponse.success(
                movieService.getAllMovies(ott),
                "Movies fetched successfully"
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getMovieById(@PathVariable Long id) {
        return ApiResponse.success(
                movieService.getMovieById(id),
                "Movie fetched successfully"
        );
    }

    @PostMapping
    public ApiResponse<?> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        return ApiResponse.success(
                movieService.createMovie(request),
                "Movie created successfully"
        );
    }
}
