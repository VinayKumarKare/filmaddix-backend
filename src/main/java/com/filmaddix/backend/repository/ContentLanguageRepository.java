package com.filmaddix.backend.repository;

import com.filmaddix.backend.entity.ContentLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentLanguageRepository
        extends JpaRepository<ContentLanguage, Long> {
}
