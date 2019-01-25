INSERT INTO VEHICLES (id, model) VALUES
  (1, 'Mercedes Benz'),
  (2, 'BMW'),
  (3, 'Toyota'),
  (4, 'Fiat');

INSERT INTO CLIENTS (id, first_name, last_name) VALUES
  (1, 'Ion', 'Tiriac'),
  (2, 'Stefan', 'Placinta'),
  (3, 'Ciprian', 'Porumbescu'),
  (4, 'Alina', 'Platon');

INSERT INTO RENT_HISTORY (id, days, vehicle_id, client_id) VALUES
  (1, 9, 1, 1),
  (2, 8, 2, 1),
  (3, 8, 3, 2),
  (4, 5, 4, 2),
  (5, 5, 1, 3),
  (6, 5, 2, 3),
  (7, 5, 3, 4),
  (8, 5, 4, 4);
