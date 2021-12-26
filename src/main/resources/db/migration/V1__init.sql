CREATE TABLE IF NOT EXISTS products
(id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
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

CREATE TABLE users (
                       id         bigserial PRIMARY KEY,
                       username   VARCHAR(36) NOT NULL,
                       password   VARCHAR(80) NOT NULL,
                       email      VARCHAR(50) UNIQUE,
                       created_at TIMESTAMP DEFAULT current_timestamp,
                       updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE roles (
                       id         bigserial PRIMARY KEY,
                       name       VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT current_timestamp,
                       updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE users_roles (
                             user_id bigint NOT NULL REFERENCES users (id),
                             role_id bigint NOT NULL REFERENCES roles (id),
                             PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2);