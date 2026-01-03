CREATE TABLE content_languages (
                                   id BIGSERIAL PRIMARY KEY,

                                   content_id BIGINT NOT NULL,

                                   language VARCHAR(100) NOT NULL,

                                   is_primary BOOLEAN DEFAULT FALSE,

                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
