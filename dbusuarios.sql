create database dbusuarios; 
show databases;
use dbusuarios;
create table usuarios(
id int primary key auto_increment,
usuario varchar(50) not null,
email varchar (50) not null,
senha varchar (50) not null
);
show tables;
describe usuarios;