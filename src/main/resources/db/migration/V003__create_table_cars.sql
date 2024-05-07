CREATE TABLE cars
(
    id           UUID PRIMARY KEY,
    model        VARCHAR(30),
    manufacturer VARCHAR(30),
    _year        VARCHAR(4),
    fipe_value   FLOAT
);

CREATE INDEX IF NOT EXISTS idx_cars_model ON cars (model);
CREATE INDEX IF NOT EXISTS idx_cars_manufacturer ON cars (manufacturer);