
insert into AUTHOR (name) values ('Daniel Defo');
insert into AUTHOR (name) values ('Samuil Marshak');
insert into AUTHOR (name) values ('Kent Bek');

insert into ADDRESS (country, town, street_and_building) values ('Russia', 'Moscow', 'Esenina st., 13-234');
insert into ADDRESS (country, town, street_and_building) values ('United States', 'Dallas', 'Independence ave., 56-12');

insert into PUBLISHER (name, address_id) values ('Blagovest', 2);
insert into PUBLISHER (name, address_id) values ('Piter', 1);

insert into BOOK (title, publisher_id) values ('Kolobok', 1);
insert into BOOK (title, publisher_id) values ('Repka', 2);
insert into BOOK (title, publisher_id) values ('Tri porosenka', 2);
insert into BOOK (title, publisher_id) values ('Buratino', 1);

insert into BOOK_AUTHOR (book_id, author_id) values (1, 1);
insert into BOOK_AUTHOR (book_id, author_id) values (2, 1);
insert into BOOK_AUTHOR (book_id, author_id) values (2, 3);
insert into BOOK_AUTHOR (book_id, author_id) values (3, 2);
insert into BOOK_AUTHOR (book_id, author_id) values (4, 1);
