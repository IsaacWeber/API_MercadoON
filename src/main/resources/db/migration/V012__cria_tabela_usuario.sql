use mercadoon;

create table usuario(
    id bigint auto_increment,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    primary key(id)
);
