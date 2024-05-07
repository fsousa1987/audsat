INSERT INTO drivers (id, document, birthdate) VALUES ('11aae36c-8e74-4c02-8617-1246dbd0879d', '662.909.241-41', '1987-01-02');
INSERT INTO drivers (id, document, birthdate) VALUES ('1ae432af-59eb-443a-9fc6-cc60d83c00b8', '862.189.432-75', '2001-01-02');

INSERT INTO customer (id, name, driver_id) VALUES ('7da5ffe9-e54b-4cca-ab09-38ae2276f65c', 'Otávio Marcos Vinicius Martins', '11aae36c-8e74-4c02-8617-1246dbd0879d');
INSERT INTO customer (id, name, driver_id) VALUES ('4e7be305-6c5c-4d64-97a4-0fce26a761cc', 'Severino Jorge da Cruz', '1ae432af-59eb-443a-9fc6-cc60d83c00b8');

INSERT INTO cars (id, model, manufacturer, _year, fipe_value) VALUES ('6ea31f9f-04ff-4124-b6d0-bf043350d07f', 'Uno', 'Fiat', 1996, 8855);
INSERT INTO cars (id, model, manufacturer, _year, fipe_value) VALUES ('f6363d8c-bf8a-4c63-bef3-b38555155884', 'Celta', 'Chevrolet', 2005, 15329);

INSERT INTO claims (id, car_id, driver_id, event_date) VALUES ('00c4d269-0ddf-40dc-8533-bdfaf36b0747', '6ea31f9f-04ff-4124-b6d0-bf043350d07f', '11aae36c-8e74-4c02-8617-1246dbd0879d', '2024-01-01');
INSERT INTO claims (id, car_id, driver_id, event_date) VALUES ('edaa4a64-6b97-4af7-a8a8-d8e06b631b12', 'f6363d8c-bf8a-4c63-bef3-b38555155884', '1ae432af-59eb-443a-9fc6-cc60d83c00b8', '2024-01-02');

INSERT INTO insurances (id, customer_id, creation_dt, updated_at, car_id, is_active) VALUES ('899674f7-0dcd-4bcf-8aab-c92358b2d251', '7da5ffe9-e54b-4cca-ab09-38ae2276f65c', '2024-02-02 00:00:00', '2024-02-02 00:00:00', '6ea31f9f-04ff-4124-b6d0-bf043350d07f', true);
INSERT INTO insurances (id, customer_id, creation_dt, updated_at, car_id, is_active) VALUES ('2b173b13-905d-4786-8bd7-633343cd27b3', '4e7be305-6c5c-4d64-97a4-0fce26a761cc', '2024-02-02 00:00:00', '2024-02-02 00:00:00', 'f6363d8c-bf8a-4c63-bef3-b38555155884', true);