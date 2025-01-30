INSERT INTO cuisine (name) values ('Brazilian');
INSERT INTO cuisine (name) values ('Japanese');

INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Bier Garden', 20.00, 1);
INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Canttone', 15.00, 2);

INSERT INTO state (name) VALUES ('Rio Grande do Sul');
INSERT INTO state (name) VALUES ('Santa Catarina');

INSERT INTO city (name, state_id) VALUES ('Campo Bom', 1);
INSERT INTO city (name, state_id) VALUES ('Balneário', 2);

insert into payment_method (description) values ('Cartão de crédito');
insert into payment_method (description) values ('Cartão de débito');
insert into payment_method (description) values ('Dinheiro');

insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 1);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 2);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 3);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (2, 3);