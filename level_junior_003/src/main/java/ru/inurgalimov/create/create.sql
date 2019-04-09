--Создать SQL скрипт инициализирующий создание новой базы данных.
CREATE DATABASE test
--Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
create table users (
	id serial primary key,
	login varchar(2000)
);

create table item (
	id serial primary key,
	number integer,
	users_id int unique references users(id)
);

create table roles (
	id serial primary key,
	descriptions varchar(2000),
	users_id int references users(id)
);

create table rules (
	id serial primary key,
	rules varchar(2000)
);

create table roles_rules (
	id serial primary key,
	checking boolean,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table states (
	id serial primary key,
	states boolean,
	item_id int references item(id)
);

create table category (
	id serial primary key,
	category varchar(2000),
	item_id int references item(id)
);

create table attaches (
	id serial primary key,
	attaches varchar(2000),
	item_id int references item(id)
);

create table com (
	id serial primary key,
	com varchar(2000),
	item_id int references item(id)
);
--Создать SQL скрипт заполняющий начальные данные для системы заявок.
insert into users (login) values ('name');
insert into item (number) values (1000);
insert into roles (descriptions) values ('test');
insert into rules (rules) values ('test');
insert into roles_rules (checking) values (true);
insert into states (states) values (false);
insert into category (category) values ('test');
insert into attaches (attaches) values ('test');
insert into com (com) values ('test');