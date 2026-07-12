insert into cozinha (id, nome) values (1, "Tailandesa");
insert into cozinha (id, nome) values (2, "Indiana");
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

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

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (1, "Thai Gourmet", 10, 1, 9, 580123111, "Avenida Cupixanga", 234, "Portão azul", "Agrião", utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (2, "Thai Delivery", 9.50, 1, 10, 58054999, "Avenida Prostibure", 871, "Placa de alerta no portão", "Cacimba de Dentro", utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (3, "Tuk Tuk Comida Indiana", 15, 2, 4, 58046600, "Avenida Hilton Souto Maior", 6701, "Quadra 756, Lote 22", "Portal do Sol", utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, 9, 580123111, "Avenida Cupixanga", 568, "Sem complemento", "Uraul", utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, 9, 580123111, "Avenida Cupixanga", 123, "Portão Preto", "Agrião", utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, 9, 580123111, "Avenida Pindamonhamgaba", 771, "Ao lado do Posto Federal", "Valentina", utc_timestamp, utc_timestamp);

insert into forma_pagamento(descricao) values ("Dinheiro");
insert into forma_pagamento(descricao) values ("Crédito");
insert into forma_pagamento(descricao) values ("Débito");
insert into forma_pagamento(descricao) values ("Pix");

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);