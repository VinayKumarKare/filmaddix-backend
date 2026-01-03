CREATE TABLE media_assets (
                              id BIGSERIAL PRIMARY KEY,

                              content_id BIGINT NOT NULL,

                              asset_type VARCHAR(50) NOT NULL,
                              asset_url TEXT NOT NULL,

                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
