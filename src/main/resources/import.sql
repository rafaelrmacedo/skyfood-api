INSERT INTO cuisine (name) values ('Brazilian');

INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Bier Garden', 20.00, 1);
INSERT INTO restaurant (name, tax_delivery, cuisine_id) VALUES ('Canttone', 15.00, 2);

INSERT INTO state (name) VALUES ('Rio Grande do Sul');
INSERT INTO state (name) VALUES ('Santa Catarina');

INSERT INTO city (name, state_id) VALUES ('Campo Bom', 1);
INSERT INTO city (name, state_id) VALUES ('Balneário', 2);

insert into payment_method (id, description) values (1, 'Cartão de crédito');
insert into payment_method (id, description) values (2, 'Cartão de débito');
insert into payment_method (id, description) values (3, 'Dinheiro');

insert into permissao (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 3),
       (3, 2),
       (3, 3);