INSERT INTO cuisine (name) values ('Japanese');
INSERT INTO cuisine (name) values ('Brazilian');

INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Bier Garden', 20.00, 1);
INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Canttone', 15.00, 2);

INSERT INTO state (name) VALUES ('Rio Grande do Sul');
INSERT INTO state (name) VALUES ('Santa Catarina');

INSERT INTO city (name, state_id) VALUES ('Campo Bom', 1);
INSERT INTO city (name, state_id) VALUES ('Balneário', 2);