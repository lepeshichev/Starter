/* Заполнение таблицы carts */
insert into products_lav.carts (promocode) values (2);
insert into products_lav.carts (promocode) values (1.5);
insert into products_lav.carts (promocode) values (1.2);

/* Заполнение таблицы clients */
insert into products_lav.clients (username, password, email, cart_id) values ('Admin', 'asdasd', 'mail@yandex.ru', 1);
insert into products_lav.clients (username, password, email, cart_id) values ('God', '12345', 'smth@yandex.ru', 2);

/* Заполнение таблицы products */
insert into products_lav.products (name, price, amount) values ('Кальмар', 370, 5);
insert into products_lav.products (name, price, amount) values ('Окунь', 260, 10);
insert into products_lav.products (name, price, amount) values ('Тунец', 800, 5);

/* Заполнение таблицы products_carts */
insert into products_lav.products_carts (id_product, id_cart, amount) values (2, 2, 1);
insert into products_lav.products_carts (id_product, id_cart, amount) values (3, 2, 5);