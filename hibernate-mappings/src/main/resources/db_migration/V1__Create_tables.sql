create table Author
(
  id   int          not null auto_increment,
  name varchar(100) not null,
  primary key (id)
);

create table Address
(
  id                int          not null auto_increment,
  country           varchar(50)  not null,
  town              varchar(50)  not null,
  street_and_building varchar(100) not null,
  primary key (id)
);

create table Publisher
(
  id         int          not null auto_increment,
  name       varchar(100) not null,
  address_id int          not null,
  primary key (id),
  foreign key (address_id) references Address(id)
);

create table Book
(
  id    int          not null auto_increment,
  title varchar(100) not null,
  publisher_id int   not null,
  primary key (id),
  foreign key (publisher_id) references Publisher(id)
);

create table db.Book_Author
(
  book_id     INT NOT NULL,
  author_id   INT NOT NULL,
  FOREIGN KEY (book_id) REFERENCES Book(id),
  FOREIGN KEY (author_id) REFERENCES Author(id)
);
