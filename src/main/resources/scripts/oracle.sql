create table rrgayer.cliente (
    id number(4),
    nome varchar(100)
);

select * from rrgayer.cliente;

update rrgayer.cliente set nome = 'Ricardo Gayer 2'
where id = 43;

commit;

delete from rrgayer.cliente
where id >= 200;

create or replace trigger rrgayer.cliente_i
    before insert on rrgayer.cliente for each row
begin
        if (:new.id >= 200) then
begin
                raise_application_error(-20001,'Falha na transacao');
end;
end if;
end;
end;

drop trigger rrgayer.cliente_i;

insert into rrgayer.cliente (id,nome) values (998,'teste 998');

select * from rrgayer.cliente
order by id;
