-- Avoid duplicate content entries
ALTER TABLE contents
    ADD CONSTRAINT uq_contents_title_release
        UNIQUE (title, content_type);

-- One rating per source
ALTER TABLE content_ratings
    ADD CONSTRAINT uq_content_rating_source
        UNIQUE (content_id, rating_source);

-- Avoid duplicate language entries
ALTER TABLE content_languages
    ADD CONSTRAINT uq_content_language
        UNIQUE (content_id, language_code);

-- One release per platform per content
ALTER TABLE content_releases
    ADD CONSTRAINT uq_content_platform_release
        UNIQUE (content_id, platform, release_type);
