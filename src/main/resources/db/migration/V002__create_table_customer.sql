CREATE TABLE IF NOT EXISTS customer
(
    id        UUID PRIMARY KEY,
    name      VARCHAR(90),
    driver_id UUID
);

ALTER TABLE customer
    ADD CONSTRAINT customer_driver_id FOREIGN KEY (driver_id) REFERENCES drivers (id);

CREATE INDEX IF NOT EXISTS idx_customer_name ON customer (name);
CREATE INDEX IF NOT EXISTS idx_customer_driver_id ON customer (driver_id);