--Задание.
--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from type as t inner join product as p on t.id = p.type_id where t.name = 'сыр';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as p where p.name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as p where p.expired_date < '2019-05-01 00:00:01';
--4. Написать запрос, который выводит самый дорогой продукт.
select * from product as p where p.price = (select max(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from type as t inner join product as p on t.id = p.type_id where t.name = 'сыр';
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from type as t inner join product as p on t.id = p.type_id where t.name = 'сыр' and t.name = 'молоко';
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
alter table product add column count integer;
alter table product alter column count set default 5;
select * from product;
update product set count = 11 where name = 'домик в деревне';
select t.name from type as t inner join product as p on t.id = p.type_id
	where p.count = (select count(10) from product);
--8. Вывести все продукты и их тип.
select * from type as t inner join product as p on t.id = p.type_id;