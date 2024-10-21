CREATE TABLE IF NOT EXISTS clearing_cost (
    id BIGSERIAL PRIMARY KEY,
    country_code VARCHAR NOT NULL,
    clearing_cost DECIMAL(10, 2) NOT NULL
);