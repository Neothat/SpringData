CREATE TABLE IF NOT EXISTS products
(
    id
    bigserial
    primary
    key,
    title
    varchar
(
    255
), score int, cost int);
INSERT INTO products (title, score, cost)
VALUES ('Bread', 0, 26);
INSERT INTO products (title, score, cost)
VALUES ('Milk', 0, 58);
INSERT INTO products (title, score, cost)
VALUES ('Apples', 0, 99);
INSERT INTO products (title, score, cost)
VALUES ('Beans', 0, 115);
INSERT INTO products (title, score, cost)
VALUES ('Eggs', 0, 90);