use mercadoon;

create table arquivo(
    id bigint auto_increment,
    nome varchar(100) not null,
    tipo varchar(100) not null,
    data blob,
    primary key(id),
);