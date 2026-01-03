ALTER TABLE content_languages
    ADD CONSTRAINT fk_content_languages_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;

ALTER TABLE content_releases
    ADD CONSTRAINT fk_content_releases_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;

ALTER TABLE media_assets
    ADD CONSTRAINT fk_media_assets_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;

ALTER TABLE content_ratings
    ADD CONSTRAINT fk_content_ratings_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;
