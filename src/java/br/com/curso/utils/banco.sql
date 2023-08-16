create table tipoproduto(
idtipoproduto serial primary key,
	descricao varchar(100) not null);
	
create table unidademedida(
idunidademedida serial primary key,
	descricao varchar(100) not null,
	sigla varchar(2) not null
);

create table tipomovimento(
idtipomovimento serial primary key,
	descricao varchar(100) not null
);

create table funcionario(
idfuncionario serial primary key,
	nomefuncionario varchar(100) not null
);

create table produto(
idproduto serial primary key,
	nomeproduto varchar(100) not null,
	ultimoprecopago float not null,
	saldoatual float not null,
	idtipoproduto int not null,
	idunidademedida int not null,
	constraint fk_tipoproduto foreign key (idtipoproduto) references tipoproduto(idtipoproduto),
	constraint fk_unidademedida foreign key (idunidademedida) references unidademedida(idunidademedida)
);

create table movimentoestoque (
idmovimento serial primary key,
	entradasaida varchar(10) not null,
	documento varchar(100) not null,
	data varchar(8) not null,
	quantidade float not null,
	valormovimentado float not null,
	idproduto int not null,
	idtipomovimento int not null,
	idfuncionario int not null,
	constraint fk_produto foreign key (idproduto) REFERENCES produto(idproduto),
	constraint fk_tipomovimento foreign key (idtipomovimento) references tipomovimento(idtipomovimento),
	constraint fk_funcionario foreign key (idfuncionario) references funcionario(idfuncionario)
)

create or replace function quantidadeProduto() returns trigger as
$$

declare 

es integer = new.quantidade;

begin

if new.entradasaida = 'Entrada' or new.entradasaida = 'entrada' THEN
	update produto set saldoatual = saldoatual + es where idproduto = new.idproduto;
ELSE
	update produto set saldoatual = saldoatual - es where idproduto = new.idproduto;
end if;

return new;

end;
$$
language plpgsql;

create trigger calcularQuantidade before insert on movimentoestoque for each row execute procedure quantidadeProduto();


insert into movimentoestoque (entradasaida, documento, "data", quantidade, valormovimentado, idproduto, idtipomovimento, idfuncionario) 
values ('saida', 'Nota Fiscal', '11/06/2023', 20, 50, 10, 1, 2);
