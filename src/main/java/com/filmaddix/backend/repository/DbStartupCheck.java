package com.filmaddix.backend;

import com.filmaddix.backend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbStartupCheck {

    @Bean
    CommandLineRunner checkDb(MovieRepository movieRepository) {
        return args -> {
            System.out.println("✅ Movie count in DB = " + movieRepository.count());
        };
    }
}
