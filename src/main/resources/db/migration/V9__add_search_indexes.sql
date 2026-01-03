CREATE INDEX idx_contents_searchable_title
    ON contents (searchable_title);

CREATE INDEX idx_contents_popularity
    ON contents (popularity_score DESC);
