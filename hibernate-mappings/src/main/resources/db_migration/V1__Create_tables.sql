
create table AUTHOR (
  ID   serial       not null primary key,
  NAME varchar(100) not null
);

create table ADDRESS (
  ID            serial       not null primary key,
  COUNTRY       varchar(50)  not null,
  TOWN          varchar(50)  not null,
  STREET_AND_BUILDING varchar(100) not null
);

create table PUBLISHER (
  ID            serial       not null primary key,
  NAME          varchar(100) not null,
  ADDRESS_ID    int          not null,
  foreign key (ADDRESS_ID) references ADDRESS(ID)
);

create table BOOK (
  ID            serial       not null primary key,
  TITLE         varchar(100) not null,
  PUBLISHER_ID  int   not null,
  foreign key (PUBLISHER_ID) references PUBLISHER(ID)
);

create table BOOK_AUTHOR (
  BOOK_ID       int not null,
  AUTHOR_ID     int not null,
  foreign key (BOOK_ID) references BOOK(ID),
  foreign key (AUTHOR_ID) references AUTHOR(ID)
);
