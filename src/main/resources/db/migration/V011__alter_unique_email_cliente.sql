use mercadoon;

alter table cliente
modify column email varchar(100) not null unique;