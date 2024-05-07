CREATE TABLE IF NOT EXISTS claims
(
    id         UUID PRIMARY KEY,
    car_id     UUID,
    driver_id  UUID,
    event_date DATE
);

ALTER TABLE claims
    ADD CONSTRAINT claims_car_id FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE;

ALTER TABLE claims
    ADD CONSTRAINT claims_driver_id FOREIGN KEY (driver_id) REFERENCES drivers (id);

CREATE INDEX IF NOT EXISTS idx_claims_car_id ON claims (car_id);
CREATE INDEX IF NOT EXISTS idx_claims_driver_id ON claims (driver_id);
