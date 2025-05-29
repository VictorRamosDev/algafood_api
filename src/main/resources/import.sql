insert into cozinha (id, nome) values (1, "Tailandesa");
insert into cozinha (id, nome) values (2, "Indiana");

insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai Gourmet", 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai Delivery", 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Tuk Tuk Comida Indiana", 15, 2);

insert into forma_pagamento(descricao) values ("Dinheiro");
insert into forma_pagamento(descricao) values ("Crédito");
insert into forma_pagamento(descricao) values ("Débito");
insert into forma_pagamento(descricao) values ("Pix");

insert into estado (nome) values ("Paraíba");
insert into estado (nome) values ("Rio Grande do Norte");
insert into estado (nome) values ("Pernambuco");
insert into estado (nome) values ("Ceará");
insert into estado (nome) values ("Piauí");
insert into estado (nome) values ("Maranhão");
insert into estado (nome) values ("Alagoas");