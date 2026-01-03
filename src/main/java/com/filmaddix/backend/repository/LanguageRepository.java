package com.filmaddix.backend.repository;

import com.filmaddix.backend.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    boolean existsByCodeIgnoreCase(String code);

    Optional<Language> findByCodeIgnoreCase(String code);
}
