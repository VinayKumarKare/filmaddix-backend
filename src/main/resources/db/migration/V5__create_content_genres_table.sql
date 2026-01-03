CREATE TABLE content_genres (
                                id BIGSERIAL PRIMARY KEY,

                                content_id BIGINT NOT NULL,
                                genre_id BIGINT NOT NULL,

                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
