
insert into ITEMS (ITEM_TYPE, NAME, PRICE, WEIGHT, LENGTH, WIDTH, HEIGHT) values
    ('3-size', 'Milk packet', 1.5, 1, 15, 5, 10);
insert into ITEMS (ITEM_TYPE, NAME, PRICE, WEIGHT, LENGTH, WIDTH) values
    ('2-size', 'Picture', 29.9, 100, 600, 15),
    ('2-size', 'Copper tube', 19.5, 140, 200, 12);
insert into ITEMS (ITEM_TYPE, NAME, PRICE, WEIGHT, LENGTH) values
    ('1-size', 'Copper cable', 20.5, 10.5, 230);
insert into ITEMS (ITEM_TYPE, NAME, PRICE, WEIGHT) values
    ('0-size', 'Toy house', 4.9, 0.05);

select count(*) from ITEMS;

-- Show info about tables in DB:
select TABLE_NAME, TABLE_TYPE, ROW_COUNT_ESTIMATE from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='PUBLIC';
