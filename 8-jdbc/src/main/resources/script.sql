create schema if not exists products_lav;

create table products_lav.products
(
    id    integer generated always as identity primary key,
    name  varchar(255) not null,
    price numeric      not null,
    amount integer      not null
);

create table products_lav.carts
(
    id        integer generated always as identity primary key,
    promocode varchar(255)
);

create table products_lav.clients
(
    id       integer generated always as identity primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255),
    cart_id  integer      not null
        constraint client_cart_id_fk
            references products_lav.carts
);

create table products_lav.products_carts
(
    id         integer generated always as identity primary key,
    id_product integer not null
        constraint product_client_products_id_fk
            references products_lav.products,
    id_cart    integer not null
        constraint product_client_cart_id_fk
            references products_lav.carts,
    amount      integer not null
);