package com.filmaddix.backend.repository;

import com.filmaddix.backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByOttPlatformIgnoreCase(String ottPlatform);

}
