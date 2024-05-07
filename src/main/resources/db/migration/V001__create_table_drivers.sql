CREATE TABLE IF NOT EXISTS drivers
(
    id        UUID PRIMARY KEY,
    document  VARCHAR(90),
    birthdate DATE
);

CREATE INDEX IF NOT EXISTS idx_drivers_document ON drivers (document);