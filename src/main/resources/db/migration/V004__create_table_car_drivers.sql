CREATE TABLE car_drivers
(
    id           UUID PRIMARY KEY,
    driver_id    UUID,
    car_id       UUID,
    is_main_driver BOOLEAN
);

ALTER TABLE car_drivers
    ADD CONSTRAINT car_drivers_driver_id FOREIGN KEY (driver_id) REFERENCES drivers (id);

ALTER TABLE car_drivers
    ADD CONSTRAINT car_drivers_car_id FOREIGN KEY (car_id) REFERENCES cars (id);

CREATE INDEX IF NOT EXISTS idx_car_drivers_driver_id ON car_drivers (driver_id);
CREATE INDEX IF NOT EXISTS idx_car_drivers_car_id ON car_drivers (car_id);