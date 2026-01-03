package com.filmaddix.backend.repository;

import com.filmaddix.backend.entity.ContentRelease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentReleaseRepository extends JpaRepository<ContentRelease, Long> {

    List<ContentRelease> findByContentId(Long contentId);
}
