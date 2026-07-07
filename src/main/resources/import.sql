insert into cozinha (id, nome) values (1, "Tailandesa");
insert into cozinha (id, nome) values (2, "Indiana");

insert into estado (id, nome) values (1, "Paraíba");
insert into estado (id, nome) values (2, "Rio Grande do Norte");
insert into estado (id, nome) values (3, "Pernambuco");
insert into estado (id, nome) values (4, "Ceará");
insert into estado (id, nome) values (5, "Paraná");
insert into estado (id, nome) values (6, "Bahia");
insert into estado (id, nome) values (7, "Alagoas");
insert into estado (id, nome) values (8, "Minas Gerais");
insert into estado (id, nome) values (9, "São Paulo");

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 8);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 8);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 9);
insert into cidade (id, nome, estado_id) values (4, 'João Pessoa', 1);
insert into cidade (id, nome, estado_id) values (5, 'Natal', 2);
insert into cidade (id, nome, estado_id) values (6, 'Recife', 3);
insert into cidade (id, nome, estado_id) values (7, 'Fortaleza', 4);
insert into cidade (id, nome, estado_id) values (8, 'Curitiba', 5);
insert into cidade (id, nome, estado_id) values (9, 'Maceió', 7);
insert into cidade (id, nome, estado_id) values (10, 'Salvador', 6);
insert into cidade (id, nome, estado_id) values (11, 'Campina Grande', 1);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values ("Thai Gourmet", 10, 1, 9, 580123111, "Avenida Cupixanga", 234, "Portão azul", "Agrião", utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values ("Thai Delivery", 9.50, 1, 10, 58054999, "Avenida Prostibur", 871, "Placa de alerta no portão", "Cacimba de Dentro", utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values ("Tuk Tuk Comida Indiana", 15, 2, 4, 58046600, "Avenida Hilton Souto Maior", 6701, "Quadra 756, Lote 22", "Portal do Sol", utc_timestamp, utc_timestamp);

insert into forma_pagamento(descricao) values ("Dinheiro");
insert into forma_pagamento(descricao) values ("Crédito");
insert into forma_pagamento(descricao) values ("Débito");
insert into forma_pagamento(descricao) values ("Pix");

insert into algafood.restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,4), (3,1), (3,4);