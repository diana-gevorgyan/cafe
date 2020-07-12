create table c_user
(
    id       bigserial     not null,
    name     varchar(255)  not null,
    type     varchar(10)   not null,
    email    varchar(255)  not null,
    password varchar(1023) not null,
    constraint c_user_id_pk primary key (id),
    constraint c_user_email_uc unique (email)
);

create table c_table
(
    id                 bigserial not null,
    number             integer   not null,
    assigned_waiter_id bigint    not null,
    constraint c_table_id_pkey primary key (id),
    constraint c_table_assigned_waiter_id_fk foreign key (assigned_waiter_id) references c_user (id),
    constraint c_table_number_uc unique (number)
);

create index c_table_assigned_waiter_id_index on c_table (assigned_waiter_id);

create table c_product
(
    id    bigserial      not null,
    name  varchar(255)   not null,
    price decimal(19, 4) not null,
    constraint c_product_id_pk primary key (id),
    constraint c_product_name_uc unique (name)
);

create table c_order
(
    id       bigserial   not null,
    table_id bigint      not null,
    status   varchar(10) not null,
    constraint c_order_id_pk primary key (id),
    constraint c_order_c_table_id_fk foreign key (table_id) references c_table (id)
);

create index c_order_c_table_id_status_index on c_order (table_id, status);

create table c_product_in_order
(
    product_id bigint  not null,
    order_id   bigint  not null,
    amount     integer not null,
    constraint c_product_in_order_product_id_order_id_pk primary key (product_id, order_id),
    constraint c_product_in_order_product_id_fk foreign key (product_id) references c_product (id),
    constraint c_product_order_id_fk foreign key (order_id) references c_order (id)
);

create index c_product_in_order_order_id_index on c_product_in_order (order_id);
