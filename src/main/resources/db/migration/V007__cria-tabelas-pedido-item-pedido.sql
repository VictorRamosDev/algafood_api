
create table item_pedido (
	id bigint not null auto_increment,
    observacao varchar(150) not null,
    preco_total decimal(19,2) not null,
    preco_unitario decimal(19,2) not null,
    quantidade integer not null,
    pedido_id bigint not null,
    produto_id bigint not null,

    primary key (id)
) engine=InnoDB charset=utf8;

create table pedido (
	id bigint not null auto_increment,
    data_cancelamento datetime(6) not null,
    data_confirmacao datetime(6) not null,
    data_criacao datetime(6) not null,
    data_entrega datetime(6) not null,
    endereco_bairro varchar(80) not null,
    endereco_cep varchar(10) not null,
    endereco_complemento varchar(100),
    endereco_logradouro varchar(100) not null,
    endereco_numero varchar(5) not null,
    status integer not null,
    sub_total decimal(19,2) not null,
    taxa_frete decimal(19,2) not null,
    valor_total decimal(19,2) not null,
    usuario_cliente_id bigint not null,
    endereco_cidade_id bigint not null,
    forma_pagamento_id bigint not null,
    restaurante_id bigint not null,

    primary key (id)
) engine=InnoDB charset=utf8;

alter table item_pedido add unique key uk_item_pedido_produto (pedido_id, produto_id);
alter table item_pedido add constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint fk_item_pedido_produto foreign key (produto_id) references produto (id);
alter table pedido add constraint fk_pedido_usuario foreign key (usuario_cliente_id) references usuario (id);
alter table pedido add constraint fk_pedido_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table pedido add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id);