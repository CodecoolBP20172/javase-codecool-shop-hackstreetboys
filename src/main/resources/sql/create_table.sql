DROP TABLE IF EXISTS todos;

CREATE TABLE todos
(
id varchar(36) PRIMARY KEY,
title varchar(40),
status varchar(10)
);

INSERT INTO todos (id, title, status) VALUES ("0", "VALAMI", "xxx")