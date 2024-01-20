
insert into AUTHOR(NAME) values
    ('Daniel Defo'),
    ('Samuil Marshak'),
    ('Kent Bek');

insert into ADDRESS(COUNTRY, TOWN, STREET_AND_BUILDING) values
    ('Russia', 'Moscow', 'Esenina st., 13-234'),
    ('United States', 'Dallas', 'Independence ave., 56-12');

insert into PUBLISHER(NAME, ADDRESS_ID) values
    ('Blagovest', 2),
    ('Piter', 1);

insert into BOOK(TITLE, PUBLISHER_ID) values
    ('Kolobok', 1),
    ('Repka', 2),
    ('Tri porosenka', 2),
    ('Buratino', 1);

insert into BOOK_AUTHOR(BOOK_ID, AUTHOR_ID) values
    (1, 1),
    (2, 1),
    (2, 3),
    (3, 2),
    (4, 1);

select count(*) from AUTHOR union select count(*) from ADDRESS union select count(*) from PUBLISHER union
    select count(*) from BOOK union select count(*) from BOOK_AUTHOR;
