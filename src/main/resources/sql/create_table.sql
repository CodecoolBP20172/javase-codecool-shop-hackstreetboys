DROP TABLE IF EXISTS todos;

CREATE TABLE todos
(
  id serial NOT NULL,
  name varchar,
  description text,
  defaultprice real,
  currency varchar,
  productcategory varchar,
  suppliername varchar
);

CREATE TABLE suppliers
(
  id serial NOT NULL,
  name varchar,
  description text,
  defaultprice real,
  currency varchar,
  productcategory varchar,
  suppliername varchar
);