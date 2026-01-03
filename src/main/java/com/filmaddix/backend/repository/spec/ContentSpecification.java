package com.filmaddix.backend.repository.spec;

import com.filmaddix.backend.entity.Content;
import com.filmaddix.backend.entity.ContentLanguage;
import com.filmaddix.backend.entity.ContentRelease;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ContentSpecification {

    private ContentSpecification() {}

    /* =========================
       SEARCH
       ========================= */

    public static Specification<Content> titleContains(String query) {
        return (root, cq, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("title")),
                    "%" + query.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Content> matchesQueryWithRelevance(String query) {
        return (root, cq, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }

            String likeQuery = "%" + query.toLowerCase() + "%";

            // 🔹 Title match (highest priority)
            var titleMatch =
                    cb.like(cb.lower(root.get("title")), likeQuery);

            // 🔹 Description match (secondary priority)
            var descriptionMatch =
                    cb.like(cb.lower(root.get("description")), likeQuery);

            // WHERE title OR description
            return cb.or(titleMatch, descriptionMatch);
        };
    }


    /* =========================
       FILTERS
       ========================= */

    public static Specification<Content> hasContentType(String contentType) {
        return (root, cq, cb) -> {
            if (contentType == null || contentType.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(
                    cb.upper(root.get("contentType").as(String.class)),
                    contentType.toUpperCase()
            );
        };
    }

    public static Specification<Content> hasLanguage(String language) {
        return (root, cq, cb) -> {
            if (language == null || language.isBlank()) {
                return cb.conjunction();
            }

            Join<Content, ContentLanguage> langJoin =
                    root.join("languages", JoinType.LEFT);

            return cb.equal(
                    cb.upper(langJoin.get("language").get("code")),
                    language.toUpperCase()
            );
        };
    }

    public static Specification<Content> hasGenre(String genre) {
        return (root, cq, cb) -> {
            if (genre == null || genre.isBlank()) {
                return cb.conjunction();
            }

            Join<Object, Object> genreJoin =
                    root.join("genres", JoinType.LEFT);

            return cb.equal(
                    cb.lower(genreJoin.get("name")),
                    genre.toLowerCase()
            );
        };
    }

    public static Specification<Content> hasReleaseType(String releaseType) {
        return (root, cq, cb) -> {
            if (releaseType == null || releaseType.isBlank()) {
                return cb.conjunction();
            }

            Join<Content, ContentRelease> releaseJoin =
                    root.join("releases", JoinType.LEFT);

            return cb.equal(
                    cb.upper(releaseJoin.get("releaseType")),
                    releaseType.toUpperCase()
            );
        };
    }
}
