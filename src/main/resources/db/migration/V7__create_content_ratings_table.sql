CREATE TABLE content_ratings (
                                 id BIGSERIAL PRIMARY KEY,

                                 content_id BIGINT NOT NULL,

                                 rating_source VARCHAR(50) NOT NULL,
                                 average_rating DECIMAL(3,1),
                                 vote_count BIGINT,

                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
