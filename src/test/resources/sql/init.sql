CREATE SEQUENCE hibernate_sequence START 1;

create table customer
(
    id bigint not null
        constraint customer_pkey
            primary key,
    birth_date date not null,
    create_date timestamp not null,
    email varchar(255),
    last_name varchar(255),
    name varchar(255),
    uuid uuid not null
        constraint uk_q8w2f8xfdoax44qc8w0epholu
            unique
);
-- -----------------------------------------

create table advert
(
    id bigint not null
        constraint advert_pkey
            primary key,
    advert_status integer not null,
    create_date timestamp not null,
    description varchar(255),
    price numeric(19,2) not null,
    title varchar(255),
    update_date timestamp not null,
    uuid uuid not null,
    customer_uuid uuid not null
        constraint fk39gy06aet6593xxxxci3f8mpj
            references customer (uuid)
);
-- -----------------------------------------