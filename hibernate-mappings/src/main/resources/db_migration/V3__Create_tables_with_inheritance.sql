
create table ITEMS (
  ID          serial        not null primary key,
  NAME        varchar(100)  not null,
  PRICE       decimal(5, 2) not null,
  WEIGHT      decimal(5, 2) not null,
  LENGTH      decimal(5, 2),
  WIDTH       decimal(5, 2),
  HEIGHT      decimal(5, 2),
  DESCRIPTION varchar(250),
  ITEM_TYPE   varchar(10)   not null
);
