package com.filmaddix.backend.dto.content;

import com.filmaddix.backend.domain.enums.ContentType;

import java.util.List;

public record ContentSummaryResponse(
        Long id,
        String title,
        ContentType contentType,
        String posterUrl,
        List<String> languages,
        List<String> genres
) {}
