INSERT INTO cuisine (name) values ('Brazilian');
INSERT INTO cuisine (name) values ('Japanese');
insert into cuisine (name) values ('Argentina');
insert into cuisine (name) values ('Brasileira');

INSERT INTO state (name) VALUES ('Rio Grande do Sul');
INSERT INTO state (name) VALUES ('Santa Catarina');

INSERT INTO city (name, state_id) VALUES ('Campo Bom', 1);
INSERT INTO city (name, state_id) VALUES ('Balneário', 2);

INSERT INTO restaurant (name, tax_delivery, address_city_id, address_cep, address_street, address_number, address_complement, address_neighborhood, cuisine_id, created_at, updated_at) VALUES ('Bier Garden', 20.00, 1, '12123-123', 'St. John Doe', '142', null, 'Ohio', 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, tax_delivery, cuisine_id, created_at, updated_at) VALUES ('Canttone', 15.00, 2, utc_timestamp, utc_timestamp);
insert into restaurant (name, tax_delivery, cuisine_id, created_at, updated_at) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurant (name, tax_delivery, cuisine_id, created_at, updated_at) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurant (name, tax_delivery, cuisine_id, created_at, updated_at) values ('Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurant (name, tax_delivery, cuisine_id, created_at, updated_at) values ('Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

insert into payment_method (description) values ('Cartão de crédito');
insert into payment_method (description) values ('Cartão de débito');
insert into payment_method (description) values ('Dinheiro');

insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 1);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 2);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (1, 3);
insert into restaurant_payment_methods (restaurant_id, payment_method_id) values (2, 3);

insert into product (name, description, price, is_active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, is_active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, is_active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, is_active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, is_active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, is_active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, is_active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into product (name, description, price, is_active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, is_active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);