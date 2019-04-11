--Нужно написать SQL скрипты:
--Создать структур данных в базе. 
--Таблицы. 
--Кузов. Двигатель, Коробка передач.
create table body (
	id serial primary key,
	color varchar(2000),
	model varchar(2000)
);
create table motor (
	id serial primary key,
	number_cylinders integer,
	powers integer,
	fuel varchar(2000)
);
create table transmission (
	id serial primary key,
	model varchar(2000),
	number_steps integer
);
--Создать структуру Машина. Машина не может существовать без данных из п.1.
create table car (
	id serial primary key,
	model varchar(2000),
	color varchar(2000),
	body_id int references body(id) not null,
	motor_id int references motor(id) not null,
	transmission_id int references transmission(id) not null
);
--Заполнить таблицы через insert.
insert into body(color, model) values ('white', 'sedan');
insert into body(color, model) values ('white', 'hatchback');
insert into body(color, model) values ('white', 'liftback');

insert into motor(number_cylinders, powers, fuel) values (4, 106, 'petrol');
insert into motor(number_cylinders, powers, fuel) values (4, 106, 'diesel');

insert into transmission(model, number_steps) values ('auto', 5);
insert into transmission(model, number_steps) values ('mechanical', 5);

insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 1, 1, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 1, 1, 2);
insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 2, 1, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 2, 1, 2);
insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 3, 1, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('lada', 'white', 3, 1, 2);
insert into car (model, color, body_id, motor_id, transmission_id) values ('toyota', 'white', 1, 1, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('toyota', 'white', 1, 1, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('toyota', 'white', 2, 2, 1);
insert into car (model, color, body_id, motor_id, transmission_id) values ('toyota', 'white', 2, 2, 1);
--1. Вывести список всех машин и все привязанные к ним детали.
select * from car as c
left outer join body as b on c.body_id = b.id
left outer join motor as m on c.motor_id = m.id
left outer join transmission as t on c.transmission_id = t.id;
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
insert into motor (number_cylinders, powers, fuel) values (8, 120, 'petrol');
insert into body (color, model) values ('black', 'sedan');
insert into transmission (model, number_steps) values ('mechanical', 6);
select * from body where id not in (select body_id from car);
select * from body as b left outer join car as c on b.id = c.body_id where c.id is null;
select * from motor where id not in (select motor_id from car);
select * from motor as m left outer join car as c on m.id = c.motor_id where c.id is null;
select * from transmission where id not in (select transmission_id from car);
select * from transmission as t left outer join car as c on t.id = c.transmission_id where c.id is null;