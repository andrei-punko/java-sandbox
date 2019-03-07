create table ITEMS
(
  id          int           not null auto_increment,
  name        varchar(100)  not null,
  price       decimal(5, 2) not null,
  weight      decimal(5, 2) not null,
  size_1      decimal(5, 2),
  size_2      decimal(5, 2),
  size_3      decimal(5, 2),
  description varchar(250),
  item_type   varchar(10)   not null,
  primary key (id)
);

insert into ITEMS (item_type, name, price, weight, size_1, size_2, size_3)
values ('Box', 'Milk packet', 1.5, 1, 15, 5, 10);
insert into ITEMS (item_type, name, price, weight, size_1, size_2)
values ('Tube', 'Iron tube', 29.9, 100, 600, 15);
insert into ITEMS (item_type, name, price, weight, size_1, size_2)
values ('Tube', 'Copper tube', 19.5, 140, 200, 12);
insert into ITEMS (item_type, name, price, weight)
values ('Item', 'Toy house', 4.9, 0.05);
