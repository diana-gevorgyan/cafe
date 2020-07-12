insert into c_user(id, name, type, email, password)
values (-1, 'Kim Jong-un', 'MANAGER', 'kim_junior@northkorea.com',
        '$2y$11$O1hm7GZcEvEJPflMFPhWeOhedvvwRVzUR7rrby4Q2Mo2WYCam52tq');
insert into c_user(id, name, type, email, password)
values (-2, 'Kim Il-sung', 'WAITER', 'kim_senior@northkorea.com',
        '$2y$11$O1hm7GZcEvEJPflMFPhWeOhedvvwRVzUR7rrby4Q2Mo2WYCam52tq');

insert into c_product (name, price)
values ('Water', 200.0);
insert into c_product (name, price)
values ('Bread', 400.0);
insert into c_product (name, price)
values ('Cheese', 1000.0);

insert into c_table (number, assigned_waiter_id)
values (1, -2);
insert into c_table (number, assigned_waiter_id)
values (2, -2);