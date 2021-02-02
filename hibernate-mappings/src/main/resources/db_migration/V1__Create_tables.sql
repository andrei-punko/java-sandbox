
create table AUTHOR
(
  id   int          not null auto_increment,
  name varchar(100) not null,
  primary key (id)
);

create table ADDRESS
(
  id                int          not null auto_increment,
  country           varchar(50)  not null,
  town              varchar(50)  not null,
  street_and_building varchar(100) not null,
  primary key (id)
);

create table PUBLISHER
(
  id         int          not null auto_increment,
  name       varchar(100) not null,
  address_id int          not null,
  primary key (id),
  foreign key (address_id) references ADDRESS(id)
);

create table BOOK
(
  id    int          not null auto_increment,
  title varchar(100) not null,
  publisher_id int   not null,
  primary key (id),
  foreign key (publisher_id) references PUBLISHER(id)
);

create table BOOK_AUTHOR
(
  book_id     INT NOT NULL,
  author_id   INT NOT NULL,
  FOREIGN KEY (book_id) REFERENCES BOOK(id),
  FOREIGN KEY (author_id) REFERENCES AUTHOR(id)
);
