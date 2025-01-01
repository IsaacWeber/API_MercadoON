use mercadoon;

create table poduto_imagem(
    produdo_id bigint not null,
    arquivo_id bigint not null,
    primary key(produdo_id, arquivo_id),
    foreign key (produdo_id) references produto(id),
    foreign key (arquivo_id) references arquivo(id)
);