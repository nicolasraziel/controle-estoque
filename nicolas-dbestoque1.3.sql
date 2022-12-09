/**
* Projeto de um sistema para gestão de estoque
* @author Nicolas Raziel
* @version 1.3
*/

create database dbestoque;
use dbestoque;

-- unique não permite valores duplicados
create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(250) not null,
perfil varchar(50) not null
);

describe usuarios;
-- drop table usuarios; 

/**************** CRUD ****************/

-- Create (inserir 5 usuários)
-- Read1 (selecionar todos os usuários)
-- Read2 (selecionar um usuário especifíco por id)
-- Update (alterar todos os dados de um usuário especifíco)
-- Delete (excluir um usuário especifíco)
-- Gerar a documentação - Modelo ER (Engenharia Reversa)


/****************************************************/

-- CREATE
insert into usuarios (usuario,login,senha,perfil) 
values ('Nicolas Raziel', 'nicolas', md5('123@senac'), 'user');

insert into usuarios (usuario,login,senha) 
values ('Amanda Sousa', 'amanda', '123@senac');

insert into usuarios (usuario,login,senha) 
values ('Anderson Valdevino', 'anderson', '123@senac');

insert into usuarios (usuario,login,senha) 
values ('Ariane', 'ariane', '123@senac');

insert into usuarios (usuario,login,senha) 
values ('Batman', 'batman', '123@senac');

select * from usuarios;

select * from usuarios where id = 1;

update usuarios set usuario = 'Nicolas Mudar', login = '123@mudar', senha = '1234' where id = 1;

update usuarios set usuario = 'Nicolas Raziel', login = 'Nick', senha = md5('123@senac') where id = 2;

delete from usuarios where id = 5; 

-- Inserindo senha criptografada (md5)

insert into usuarios (usuario,login,senha,perfil)
values ('Robson Vaamonde','vava',md5('123456'),'admin');

insert into usuarios (usuario,login,senha,perfil) 
values ('Nicolas Raziel', 'nicolas', md5('123@senac'), 'user');

insert into usuarios (usuario,login,senha,perfil) 
values ('Sirlene Sanches', 'sisa', md5('123456'), 'user');

-- login (usuario e senha correspondente)
select * from usuarios where perfil='user';
select * from usuarios where login='sisa' and senha = md5('123456');

create table Fornecedores (
idFor int primary key auto_increment,
razaoSocial varchar(50) not null,
fantasia varchar(50) not null,
cnpj varchar(20) unique,
ie varchar(20) unique,
cep varchar(10) not null,
endereco varchar(50) not null,
numero varchar(6) not null,
complemento varchar(20),
bairro varchar(50) not null,
cidade varchar(50) not null,
uf char(2) not null,
nomeContato varchar(30) not null,
fone varchar(15) not null,
whatsapp varchar(15) not null,
email varchar(50) not null,
site varchar(50),
obs varchar(250)




);

insert into Fornecedores (razaoSocial, fantasia, cnpj, ie, cep, 
endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone,
whatsapp, email, site, obs)
values ('AMSTEL COMERCIO E SERVICOS LTDA', 'Amstel', '24195460000180', ' ', '36080-210', 'R GENERAL GOMES CARNEIRO',
'58', ' ', 'JUIZ DE FORA', 'MINAS GERAIS', 'MG', 'RICARDO AUGUSTO', '32 3215-7413', '9999-1111',
'albcontabilidade@gmail.com', 'https://www.amstelbrasil.com/', ' ');

select * from Fornecedores;

insert into Fornecedores (razaoSocial, fantasia, cnpj, ie, cep, 
endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone,
whatsapp, email, site, obs)
values ('AMBEV S.A', 'Ambev', '07.526.5570001-00', '142.270.790.110', '04530-001', 'RUA DOUTOR RENATO PAES DE BARROS',
'1017', ' ', 'MOOCA', 'SÃO PAULO', 'SP', 'JORGE PAULO', '11 3024-6200', '9999-2222',
'opobrigaces@ambev.com.br', 'https://www.ambev.com/', 'FABRICAÇÃO DE CERVEJAS E CHOPES');

insert into Fornecedores (razaoSocial, fantasia, cnpj, ie, cep, 
endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone,
whatsapp, email, site, obs)
values ('LOJAS AMERICANAS S.A.', 'Americanas', '33.014.556/0001-96', '85.687.08-5', '20081-902', 'RUA SACADURA CABRAL',
'102', ' ', 'SAUDE', 'RIO DE JANEIRO', 'RJ', 'JORGE PAULO', '21 2206-6708', '9999-3333',
'sede@lasa.com.br', 'https://www.americanas.com.br/', 'COMÉRCIO VAREJISTA DE MERCADORIAS EM GERAL, COM PREDOMINÂNCIA DE PRODUTOS ALIMENTÍCIOS - SUPERMERCADOS');

-- pesquisa avançada filtrando por letras
select idFor as ID, fantasia as Fornecedor, fone, nomeContato from Fornecedores where fantasia like ('a%');

describe Fornecedores;

/*
Relacionamento de tabelas 1 - N (um para muitos)
Chave estrangeira (FK) - (PK)
*/

create table produtos (
codigo int primary key auto_increment, 
barcode varchar(255) unique,
produto varchar(50) not null,
descricao varchar(255),
fabricante varchar(50) not null,
-- timestamp default current_timestamp obtém automaticamente a data e hora do cadastro 
datacad timestamp default current_timestamp,
-- date (tipo de dado relacionado a datas)
dataval date, 
estoque int not null,
estoquemin int not null,
unidade char(2) not null,
localizacao varchar(50),
-- decimal(10,2) (tipo de dado relacionado a números não inteiros)
-- decimal(10,2) (10 digitos com 2 casas decimais)
custo decimal(10,2) not null,
lucro decimal(10,2),
-- idFor (chave estrangeira) usar mesmo nome e tipo de dados da chave primaria(PK) da tabela pai
idFor int not null,
foreign key(idFor) references Fornecedores(idFor)



);

describe produtos;

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('111111', 'Caneta Bic azul', 'Caneta Bic cor azul, ponta fina cx 50', 'BIC', 20231122, 20,5, 'CX', 'Prateleira 2',
38.50, 20, 7);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('222222', 'Caneta Bic Vermelha', 'Caneta Bic cor vermelha, ponta fina cx 50', 'BIC', 20231122, 15,2, 'CX', 'Prateleira 2',
40.50, 20, 7);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('333333', 'Borracha branca', 'Caneta Bic cor azul, ponta fina cx 50', 'StAEDTLER', 20231122, 30,4, 'CX', 'Prateleira 1',
20.50, 20, 7);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('444444', 'Cola Bastão', 'Cola batão pritt', 'PRITT', 20221002, 10,1, 'UN', 'Prateleira 1',
20.50, 20, 6);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('55555', 'Mouse logitech', 'Mouse usb logitech 2 botões', 'LOGITECH', 20271122, 10,15, 'UN', 'Prateleira 4',
18, 30, 6);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('666666', 'Régua 30cm', 'Régua de acrilico 30cm', 'FABER CASTELL', 20251122, 30,5, 'UN', 'Prateleira 1',
2.50, 25, 6);

insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor)
values ('7896572022785', 'Caneta ponta fina', 'Caneta 2 UN azul e vermelha', 'Bic', 20251122, 50,20, 'UN', 'Prateleira 5',
300, 50, 6);



select * from produtos;

delete from produtos where codigo = 8;

/*
Relatórios (select especial)
*/

-- relatório 1 (unificar produtos com fornecedores)
-- produtos.idFor (FK) e Fornecedores.idFor (PK)
select * from produtos inner join Fornecedores on produtos.idFor = Fornecedores.idFor;

-- relatório 2 (fornecedor relacionado ao produto)
select produtos.codigo as Código, produtos.produto as Produto, produtos.custo as Valor, 
Fornecedores.fantasia as Fornecedor 
from produtos inner join Fornecedores on produtos.idFor = Fornecedores.idFor;

-- relatório 3 (inventário do estoque)
select sum(estoque * custo) as Total from produtos;

-- relatório 4 (calcular o preço de venda)
select codigo as Código, produto, custo, 
(custo + (custo * lucro)/100) as Venda from produtos;

-- relatório 5 (reposição de estoque)
-- ,'%d/%m/%Y (dd/mm/aaaa) ,'%d/%m/%y (dd/mm/aa)
select codigo as Código, produto, date_format (dataval,'%d/%m/%Y') as data_validade, estoque, estoquemin as estoque_mínimo
from produtos where estoque < estoquemin;

-- relatório 5.1 (versão impressão)
select codigo,produto,
date_format(dataval,'%d/%m/%Y'),
estoque, estoquemin
from produtos where estoque < estoquemin;


-- relatório 6 (produtos vencidos)
select codigo as Código, produto, localizacao as Localização, date_format (dataval,'%d/%m/%Y') as data_validade, 
datediff(dataval, curdate()) as dias_vencidos from produtos where datediff(dataval, curdate()) < 0;

delete from Fornecedores where idFor = 1;

delete from Fornecedores where idFor = 6;

delete from produtos where codigo = 17;


-- create table clientes (
-- idCli int primary key auto_increment,
-- nome varchar(50) not null,
-- numero varchar(6) not null,

-- );

select * from produtos;
select * from produtos where codigo = ?;
select * from produtos where barcode = 111111;

create table clientes (
IdCli int primary key auto_increment, 
nome varchar(50) not null,
telefone varchar(15) not null,
cpf varchar(20) unique,
profissao varchar(50) not null,
cep varchar(10) not null,
endereco varchar(50) not null,
numero varchar(6) not null,
complemento varchar(50),
bairro varchar (50) not null,
cidade varchar (20) not null,
email varchar(50) not null

);

describe clientes;

select * from clientes;

delete from clientes where idCli = '5';

insert into clientes (nome,telefone,cpf,profissao,cep,endereco,numero,complemento,bairro,cidade,email) 
values ('Anderson Valdevino', '9999-9999', '464001122', 'Comerciante', '000340', 'Emilio Retrosi', '200', ' ', 'Marilu','São Paulo', 'anderson@anderson.com');































