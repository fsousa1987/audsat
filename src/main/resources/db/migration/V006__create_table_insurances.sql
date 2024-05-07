CREATE TABLE IF NOT EXISTS insurances
(
    id          UUID PRIMARY KEY,
    customer_id UUID,
    car_id      UUID,
    creation_dt TIMESTAMP,
    updated_at  TIMESTAMP,
    is_active   BOOLEAN
);

ALTER TABLE insurances
    ADD CONSTRAINT insurances_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE insurances
    ADD CONSTRAINT insurances_car_id FOREIGN KEY (car_id) REFERENCES cars (id);

CREATE INDEX IF NOT EXISTS idx_insurances_customer_id ON insurances (customer_id);
CREATE INDEX IF NOT EXISTS idx_insurances_car_id ON insurances (car_id);