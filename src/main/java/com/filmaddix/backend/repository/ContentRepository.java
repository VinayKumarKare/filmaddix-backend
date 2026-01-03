package com.filmaddix.backend.repository;

import com.filmaddix.backend.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {

    // 🔍 MVP – Discovery (list page)
    @Query("""
        SELECT DISTINCT c
        FROM Content c
        LEFT JOIN FETCH c.languages
        LEFT JOIN FETCH c.mediaAssets
        """)
    List<Content> findAllForDiscovery();

    // 🔎 MVP – Detail page
    @Query("""
        SELECT DISTINCT c
        FROM Content c
        LEFT JOIN FETCH c.languages
        LEFT JOIN FETCH c.genres
        LEFT JOIN FETCH c.releases
        LEFT JOIN FETCH c.mediaAssets
        WHERE c.id = :id
        """)
    Optional<Content> findByIdWithDetails(@Param("id") Long id);

    @Query("""
        SELECT DISTINCT c
        FROM Content c
        LEFT JOIN FETCH c.languages cl
        LEFT JOIN FETCH cl.language
        LEFT JOIN FETCH c.genres
        """)
    List<Content> findAllForSummary();

    /**
     * HOME PAGE – Paginated content list
     * Lightweight query (no joins)
     */
    Page<Content> findAll(Pageable pageable);

    Page<Content> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

}
