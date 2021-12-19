CREATE TABLE IF NOT EXISTS products
(id bigserial primary key, title varchar(255), cost int);
INSERT INTO products (title, cost)
VALUES ('Bread', 26);
INSERT INTO products (title, cost)
VALUES ('Milk', 58);
INSERT INTO products (title, cost)
VALUES ('Apples', 99);
INSERT INTO products (title, cost)
VALUES ('Beans', 115);
INSERT INTO products (title, cost)
VALUES ('Eggs', 90);