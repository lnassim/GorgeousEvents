drop database gorgeouseventdb;
drop user gorgeousevent;
create user gorgeousevent with password 'password';
create database gorgeouseventdb with template=template0 owner=gorgeousevent;
\connect gorgeouseventdb;
alter default privileges grant all on tables to gorgeousevent;
alter default privileges grant all on sequences to gorgeousevent;

create table users(
    user_id integer primary key not null,
    nom_utilisateur varchar(20) not null,
    email varchar(30) not null,
    password text not null
);

creat sequence users increment 1 start 1;