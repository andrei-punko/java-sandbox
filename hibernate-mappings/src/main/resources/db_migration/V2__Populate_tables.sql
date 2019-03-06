insert into Author (name)
values ('Daniel Defo');
insert into Author (name)
values ('Samuil Marshak');
insert into Author (name)
values ('Kent Bek');

insert into Address (country, town, street_and_building)
values ('Belarus', 'Minsk', 'Rafieva st., 78-344');
insert into Address (country, town, street_and_building)
values ('Russia', 'Moscow', 'Esenina st., 13-234');
insert into Address (country, town, street_and_building)
values ('United States', 'Dallas', 'Independence ave., 56-12');

insert into Publisher (name, address_id)
values ('Blagovest', 2);
insert into Publisher (name, address_id)
values ('Piter', 1);
insert into Publisher (name, address_id)
values ('Ecsmo', 3);

insert into Book (title, publisher_id)
values ('Kolobok', 2);
insert into Book (title, publisher_id)
values ('Repka', 3);
insert into Book (title, publisher_id)
values ('Tri porosenka', 3);

insert into Book_Author (book_id, author_id)
values (1, 1);
insert into Book_Author (book_id, author_id)
values (2, 1);
insert into Book_Author (book_id, author_id)
values (2, 3);
insert into Book_Author (book_id, author_id)
values (3, 2);
