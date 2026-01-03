package com.filmaddix.backend.service;

import com.filmaddix.backend.domain.enums.ReleaseType;
import com.filmaddix.backend.dto.ContentSummaryDto;
import com.filmaddix.backend.dto.PagedResponse;
import com.filmaddix.backend.dto.PrimaryReleaseDto;
import com.filmaddix.backend.dto.content.ContentDetailsResponse;
import com.filmaddix.backend.dto.content.ContentDetailsResponse.MediaAssetDto;
import com.filmaddix.backend.dto.content.ContentDetailsResponse.ReleaseDto;
import com.filmaddix.backend.dto.content.ContentSummaryResponse;
import com.filmaddix.backend.entity.*;
import com.filmaddix.backend.exception.ResourceNotFoundException;
import com.filmaddix.backend.repository.ContentRepository;
import com.filmaddix.backend.repository.spec.ContentSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    /* =====================================================
       HOME PAGE – LEGACY (NON-PAGINATED)
       ===================================================== */

    public List<ContentSummaryResponse> getAllContentSummaries() {
        return contentRepository.findAllForSummary()
                .stream()
                .map(this::mapToSummaryResponse)
                .toList();
    }

    /* =====================================================
       HOME PAGE – PAGINATED
       ===================================================== */

    public PagedResponse<ContentSummaryDto> getHomePageSummaries(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        Page<Content> contentPage = contentRepository.findAll(pageable);
        return buildPagedSummaryResponse(contentPage);
    }

    /* =====================================================
       HOME PAGE – FILTERED + PAGINATED
       ===================================================== */

    public PagedResponse<ContentSummaryDto> getFilteredHomePageSummaries(
            int page,
            int size,
            String sortBy,
            String direction,
            String contentType,
            String language,
            String genre,
            String releaseType
    ) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        Specification<Content> spec =
                Specification.where(ContentSpecification.hasContentType(contentType))
                        .and(ContentSpecification.hasLanguage(language))
                        .and(ContentSpecification.hasGenre(genre))
                        .and(ContentSpecification.hasReleaseType(releaseType));

        Page<Content> contentPage = contentRepository.findAll(spec, pageable);
        return buildPagedSummaryResponse(contentPage);
    }

    /* =====================================================
       HOME PAGE – SEARCH (TITLE-BASED, FAST)
       ===================================================== */

    public PagedResponse<ContentSummaryDto> searchHomeContents(
            String query,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        Page<Content> contentPage =
                contentRepository.findByTitleContainingIgnoreCase(query, pageable);

        return buildPagedSummaryResponse(contentPage);
    }

    /* =====================================================
       HOME PAGE – SEARCH + FILTER (DISCOVER API)
       ===================================================== */

    public PagedResponse<ContentSummaryDto> searchAndFilterHomeContents(
            String query,
            int page,
            int size,
            String sortBy,
            String direction,
            String contentType,
            String language,
            String genre,
            String releaseType
    ) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);

        Specification<Content> spec =
                Specification.where(ContentSpecification.titleContains(query))
                        .and(ContentSpecification.hasContentType(contentType))
                        .and(ContentSpecification.hasLanguage(language))
                        .and(ContentSpecification.hasGenre(genre))
                        .and(ContentSpecification.hasReleaseType(releaseType));

        Page<Content> contentPage = contentRepository.findAll(spec, pageable);
        return buildPagedSummaryResponse(contentPage);
    }

    /* =====================================================
       CONTENT DETAILS PAGE
       ===================================================== */

    public ContentDetailsResponse getContentDetails(Long contentId) {
        Content content = contentRepository.findByIdWithDetails(contentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Content not found with id: " + contentId)
                );
        return mapToDetailsResponse(content);
    }

    /* =====================================================
       INTERNAL HELPERS
       ===================================================== */

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return PageRequest.of(page, size, sort);
    }

    private PagedResponse<ContentSummaryDto> buildPagedSummaryResponse(Page<Content> contentPage) {
        List<ContentSummaryDto> summaries = contentPage
                .getContent()
                .stream()
                .map(this::mapToSummary)
                .toList();

        return new PagedResponse<>(
                summaries,
                contentPage.getNumber(),
                contentPage.getSize(),
                contentPage.getTotalElements(),
                contentPage.getTotalPages(),
                contentPage.isFirst(),
                contentPage.isLast(),
                contentPage.hasNext()
        );
    }

    /* =====================================================
       SUMMARY MAPPERS
       ===================================================== */

    private ContentSummaryResponse mapToSummaryResponse(Content content) {

        List<String> languages = content.getLanguages()
                .stream()
                .map(ContentLanguage::getLanguage)
                .map(Language::getName)
                .distinct()
                .toList();

        List<String> genres = content.getGenres()
                .stream()
                .map(Genre::getName)
                .toList();

        return new ContentSummaryResponse(
                content.getId(),
                content.getTitle(),
                content.getContentType(),
                content.getPosterUrl(),
                languages,
                genres
        );
    }

    private ContentSummaryDto mapToSummary(Content content) {

        List<String> languages = content.getLanguages()
                .stream()
                .map(ContentLanguage::getLanguage)
                .map(Language::getName)
                .distinct()
                .toList();

        Optional<ContentRelease> primaryReleaseOpt = content.getReleases()
                .stream()
                .sorted(
                        Comparator
                                .comparing(
                                        (ContentRelease r) ->
                                                ReleaseType.THEATRICAL.name()
                                                        .equalsIgnoreCase(r.getReleaseType()) ? 0 : 1
                                )
                                .thenComparing(ContentRelease::getReleaseDate)
                )
                .findFirst();

        PrimaryReleaseDto primaryRelease = primaryReleaseOpt
                .map(r -> new PrimaryReleaseDto(
                        r.getReleaseType(),
                        r.getReleaseDate()
                ))
                .orElse(null);

        return new ContentSummaryDto(
                content.getId(),
                content.getTitle(),
                content.getContentType().name(),
                languages,
                primaryRelease,
                content.getPosterUrl()
        );
    }

    /* =====================================================
       DETAILS MAPPERS
       ===================================================== */

    private ContentDetailsResponse mapToDetailsResponse(Content content) {

        List<String> languages = content.getLanguages()
                .stream()
                .map(ContentLanguage::getLanguage)
                .map(Language::getName)
                .distinct()
                .toList();

        List<String> genres = content.getGenres()
                .stream()
                .map(Genre::getName)
                .toList();

        List<ReleaseDto> releases = content.getReleases()
                .stream()
                .map(this::mapRelease)
                .toList();

        List<MediaAssetDto> mediaAssets = content.getMediaAssets()
                .stream()
                .map(this::mapMediaAsset)
                .toList();

        return new ContentDetailsResponse(
                content.getId(),
                content.getTitle(),
                content.getDescription(),
                content.getContentType(),
                content.getPosterUrl(),
                languages,
                genres,
                releases,
                mediaAssets
        );
    }

    private ReleaseDto mapRelease(ContentRelease release) {
        return new ReleaseDto(
                release.getReleaseType(),
                release.getReleaseDate(),
                release.getPlatform(),
                release.getWatchUrl()
        );
    }

    private MediaAssetDto mapMediaAsset(MediaAsset asset) {
        return new MediaAssetDto(
                asset.getType(),
                asset.getUrl()
        );
    }
}
