CREATE SEQUENCE hibernate_sequence START 1;

create table customer
(
    id         bigint not null
        constraint customer_pkey
            primary key,
    birth_date date   not null,
    email      varchar(255),
    last_name  varchar(255),
    name       varchar(255)
);
-- -----------------------------------------

create table advert
(
    id          bigint         not null
        constraint advert_pkey
            primary key,
    create_date timestamp      not null,
    description varchar(255),
    price       numeric(19, 2) not null,
    title       varchar(255),
    customer_id bigint         not null
        constraint fktmyrvq1quok34rjlguxkkei40
            references customer
);
-- -----------------------------------------