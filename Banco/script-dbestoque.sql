/**
* Projeto de um sistema para gestão de estoque
* @author Nicolas Raziel
* @version 1.0
*/

create database dbestoque;
use dbestoque;

create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null,
senha varchar(250) not null
);

describe usuarios;

/**************** CRUD ****************/

-- Create (inserir 5 usuários)
-- Read1 (selecionar todos os usuários)
-- Read2 (selecionar um usuário especifíco por id)
-- Update (alterar todos os dados de um usuário especifíco)
-- Delete (excluir um usuário especifíco)
-- Gerar a documentação - Modelo ER (Engenharia Reversa)


/****************************************************/

-- CREATE
insert into usuarios (usuario,login,senha) 
values ('Nicolas Raziel', 'nicolas', '123@senac');

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

delete from usuarios where id = 5; 









