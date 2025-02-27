alter table usuario
    add column cliente_id bigint,
    add constraint foreign key(cliente_id) references cliente(id);