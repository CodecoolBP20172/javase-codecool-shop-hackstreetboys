-- DROP primary keys
ALTER TABLE IF EXISTS ONLY public.codeshopusers DROP CONSTRAINT IF EXISTS pk_codeshopusers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS pk_products_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.suppliers DROP CONSTRAINT IF EXISTS pk_suppliers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.productcategory DROP CONSTRAINT IF EXISTS pk_prodcat_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS pk_ordered_id CASCADE;


-- DROP foreign keys
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_suppliers_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_prodcat_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.ordered_products DROP CONSTRAINT IF EXISTS fk_order_id CASCADE;


--Create Tables
DROP TABLE if EXISTS public.codeshopusers;
CREATE TABLE public.codeshopusers(
  id serial NOT NULL,
  user_name varchar,
  user_password varchar
);


DROP TABLE if EXISTS public.products;
CREATE TABLE public.products
(
  id serial PRIMARY KEY,
  name varchar,
  description text,
  defaultprice real,
  currency varchar,
  prodcat_id integer NOT NULL,
  supplier_id integer NOT NULL
);


DROP TABLE if EXISTS public.suppliers;
CREATE TABLE public.suppliers
(
  id serial NOT NULL,
  name varchar,
  description text 
);


DROP TABLE if EXISTS public.productcategory;
CREATE TABLE public.productcategory(
  id serial PRIMARY KEY,
  name varchar,
  department varchar,
  description text
);


DROP TABLE if EXISTS public.orders;
CREATE TABLE public.orders(
  id serial PRIMARY KEY,
  user_id integer,
  status varchar
);


DROP TABLE if EXISTS public.ordered_products;
DROP SEQUENCE IF EXISTS public.ordered_id_sq;
CREATE TABLE public.ordered_products(
  id serial PRIMARY KEY,
  prod_id integer NOT NULL,
  quantity integer NOT NULL,
  order_id integer NOT NULL
);


-- set primary keys
ALTER TABLE ONLY codeshopusers
    ADD CONSTRAINT pk_codeshopusers_id PRIMARY KEY (id);
ALTER TABLE ONLY products
    ADD CONSTRAINT pk_products_id PRIMARY KEY (id);
ALTER TABLE ONLY suppliers
    ADD CONSTRAINT pk_suppliers_id PRIMARY KEY (id);
ALTER TABLE ONLY productcategory
    ADD CONSTRAINT pk_productcategory_id PRIMARY KEY (id);
ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_order_id PRIMARY KEY (id);
ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT pk_ordered_id PRIMARY KEY (id);


--set foreign keys
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_suppliers_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_prodcat_id FOREIGN KEY (prodcat_id) REFERENCES productcategory(id);
ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES codeshopusers(id);
ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_product_id FOREIGN KEY (prod_id) REFERENCES products(id);
ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id);


