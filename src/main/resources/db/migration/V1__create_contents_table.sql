CREATE TABLE contents (
                          id BIGSERIAL PRIMARY KEY,

                          title VARCHAR(255) NOT NULL,

                          content_type VARCHAR(50) NOT NULL,
    -- MOVIE | SERIES

                          theatrical_release_date DATE NULL,
                          ott_release_date DATE NULL,

                          primary_language VARCHAR(100) NOT NULL,

                          poster_url TEXT NULL,
                          trailer_url TEXT NULL,
                          watch_url TEXT NULL,

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
