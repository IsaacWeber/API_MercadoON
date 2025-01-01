use mercadoon;

create table produto_imagem(
    produto_id bigint not null,
    arquivo_id bigint not null,
    primary key(produto_id, arquivo_id),
    foreign key (produto_id) references produto(id),
    foreign key (arquivo_id) references arquivo(id)
);