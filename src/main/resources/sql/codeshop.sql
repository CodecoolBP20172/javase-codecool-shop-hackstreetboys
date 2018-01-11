ALTER TABLE IF EXISTS ONLY public.users DROP CONSTRAINT IF EXISTS pk_users_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS pk_products_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.suppliers DROP CONSTRAINT IF EXISTS pk_suppliers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.productcategories DROP CONSTRAINT IF EXISTS pk_productcategories_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS pk_ordered_products_id CASCADE;


-- DROP foreign keys
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_suppliers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_productcategory_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS fk_order_id CASCADE;


--Create Tables
DROP TABLE if EXISTS public.users;
DROP SEQUENCE IF EXISTS public.users_id_seq;
CREATE TABLE public.users(
  id serial NOT NULL UNIQUE ,
  user_name varchar,
  user_password varchar
);


DROP TABLE if EXISTS public.suppliers;
DROP SEQUENCE IF EXISTS public.suppliers_id_seq;
CREATE TABLE public.suppliers
(
  id serial NOT NULL UNIQUE ,
  name varchar,
  description varchar
);


DROP TABLE if EXISTS public.productcategories;
DROP SEQUENCE IF EXISTS public.productcategories_id_seq;
CREATE TABLE public.productcategories
(
  id serial NOT NULL UNIQUE ,
  name varchar,
  department varchar,
  description varchar
);


DROP TABLE if EXISTS public.products;
DROP SEQUENCE IF EXISTS public.products_id_seq;
CREATE TABLE public.products
(
  id serial NOT NULL UNIQUE ,
  name varchar,
  description varchar,
  defaultprice real,
  currency varchar,
  productcategory_id integer NOT NULL,
  supplier_id integer NOT NULL
);


DROP TABLE if EXISTS public.orders;
DROP SEQUENCE IF EXISTS public.orders_id_seq;
CREATE TABLE public.orders
(
  id serial NOT NULL,
     user_id integer,
     numberOfProducts integer,
  totalPrice float
);


DROP TABLE if EXISTS public.ordered_products;
DROP SEQUENCE IF EXISTS public.ordered_products_id_seq;
CREATE TABLE public.ordered_products
(
  id serial NOT NULL UNIQUE ,
  product_id integer NOT NULL,
  quantity integer NOT NULL,
  order_id integer NOT NULL
);


-- set primary keys
ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users_id PRIMARY KEY (id);

ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_suppliers_id PRIMARY KEY (id);

ALTER TABLE ONLY productcategories
    ADD CONSTRAINT pk_productcategories_id PRIMARY KEY (id);

ALTER TABLE ONLY products
  ADD CONSTRAINT pk_products_id PRIMARY KEY (id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_orders_id PRIMARY KEY (id);

ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT pk_ordered_products_id PRIMARY KEY (id);


--set foreign keys
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_suppliers_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_productcategories_id FOREIGN KEY (productcategory_id) REFERENCES productcategories(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id);


-- insert values

INSERT INTO suppliers VALUES(1, 'Amazon', 'Digital content and services');
INSERT INTO suppliers VALUES(2, 'Lenovo', 'Computers');
INSERT INTO suppliers VALUES(3, 'Apple', 'Electronic devices');
INSERT INTO suppliers VALUES(4, 'Nokia', 'Electronic devices');
SELECT pg_catalog.setval('suppliers_id_seq', 4, true);


INSERT INTO productcategories VALUES(1, 'Tablet', 'Hardware', 'A tablet computer');
INSERT INTO productcategories VALUES(2, 'Phone', 'Hardware', 'A phone computer');
INSERT INTO productcategories VALUES(3, 'Computer', 'Hardware', 'A computer is a  computer');
SELECT pg_catalog.setval('productcategories_id_seq', 3, true);

INSERT INTO products VALUES (1, 'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', 2, 1);
INSERT INTO products VALUES (2, 'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', 1, 2);
INSERT INTO products VALUES (3, 'Amazon Fire HD 8', 'Amazons latest Fire HD 8 tablet is a great value for media consumption.', 89, 'USD', 1, 1);
INSERT INTO products VALUES (4, 'Apple Iphone 5S', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.', 178, 'USD', 2, 3);
INSERT INTO products VALUES (5, 'Apple MacBook Air', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.', 898, 'USD', 3, 3);
INSERT INTO products VALUES (6, 'Apple Ipad', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.', 311, 'USD', 1, 3);
INSERT INTO products VALUES (7, 'Nokia 3310', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.', 60, 'USD', 2, 4);
SELECT pg_catalog.setval('products_id_seq', 7, true);

INSERT INTO users VALUES  (1, 'Dani(k)', '1111');
SELECT pg_catalog.setval('users_id_seq', 1, true);

INSERT INTO  orders VALUES  (1, 1, 0, 0);
SELECT pg_catalog.setval('orders_id_seq', 1, true);

INSERT INTO ordered_products VALUES (1,7,1,1);
SELECT pg_catalog.setval('ordered_products_id_seq', 1, true);

