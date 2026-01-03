CREATE TABLE content_releases (
                                  id BIGSERIAL PRIMARY KEY,

                                  content_id BIGINT NOT NULL,

                                  release_type VARCHAR(50) NOT NULL,
    -- THEATRICAL | OTT

                                  platform VARCHAR(100),
    -- Netflix, Prime Video, Disney+ Hotstar, etc
    -- NULL allowed for theatrical

                                  release_date DATE,

                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
