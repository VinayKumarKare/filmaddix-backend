package com.filmaddix.backend.controller;

import com.filmaddix.backend.dto.ApiResponse;
import com.filmaddix.backend.dto.ContentSummaryDto;
import com.filmaddix.backend.dto.PagedResponse;
import com.filmaddix.backend.dto.content.ContentDetailsResponse;
import com.filmaddix.backend.dto.content.ContentSummaryResponse;
import com.filmaddix.backend.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * HOME PAGE API (LEGACY – non-paginated)
     */
    @GetMapping
    public ApiResponse<List<ContentSummaryResponse>> getAllContents() {
        return ApiResponse.success(
                contentService.getAllContentSummaries(),
                "Contents fetched successfully"
        );
    }

    /**
     * HOME PAGE API – PAGINATED (RECOMMENDED)
     */
    @GetMapping("/home")
    public ApiResponse<PagedResponse<ContentSummaryDto>> getHomePageContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return ApiResponse.success(
                contentService.getHomePageSummaries(page, size, sortBy, direction),
                "Home page contents fetched successfully"
        );
    }

    /**
     * CONTENT DETAIL PAGE API
     */
    @GetMapping("/{id}")
    public ApiResponse<ContentDetailsResponse> getContentById(@PathVariable Long id) {
        return ApiResponse.success(
                contentService.getContentDetails(id),
                "Content fetched successfully"
        );
    }

    /**
     * HOME PAGE API – FILTERED + PAGINATED (PRIMARY)
     * ✅ Stable DTO response (NO PageImpl warning)
     */
    @GetMapping("/home/filtered")
    public ApiResponse<PagedResponse<ContentSummaryDto>> getFilteredHomePageContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,

            // Optional filters
            @RequestParam(required = false) String contentType,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String releaseType
    ) {
        return ApiResponse.success(
                contentService.getFilteredHomePageSummaries(
                        page,
                        size,
                        sortBy,
                        direction,
                        contentType,
                        language,
                        genre,
                        releaseType
                ),
                "Filtered home page contents fetched successfully"
        );
    }

    /**
     * HOME PAGE SEARCH API
     * Example:
     * /api/v1/contents/home/search?query=incep
     */
    @GetMapping("/home/search")
    public ApiResponse<PagedResponse<ContentSummaryDto>> searchHomeContents(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return ApiResponse.success(
                contentService.searchHomeContents(
                        query,
                        page,
                        size,
                        sortBy,
                        direction
                ),
                "Search results fetched successfully"
        );
    }

    /**
     * HOME PAGE – SEARCH + FILTER + PAGINATION (PRIMARY DISCOVERY API)
     *
     * Example:
     * /api/v1/contents/home/discover?query=incep&language=EN&genre=Sci-Fi
     */
    @GetMapping("/home/discover")
    public ApiResponse<PagedResponse<ContentSummaryDto>> discoverHomeContents(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,

            @RequestParam(required = false) String contentType,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String releaseType
    ) {
        return ApiResponse.success(
                contentService.searchAndFilterHomeContents(
                        query,
                        page,
                        size,
                        sortBy,
                        direction,
                        contentType,
                        language,
                        genre,
                        releaseType
                ),
                "Discover contents fetched successfully"
        );
    }




}
