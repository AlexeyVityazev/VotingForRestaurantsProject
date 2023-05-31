INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name)
VALUES ('Astoria'),
       ('Italian'),
       ('Tokio City'),
       ('U Gali');

INSERT INTO DISH (name, price, rest_id)
VALUES ('soup', 5, 1),
       ('soup#2', 4, 1),
       ('tea', 2, 1),
       ('rice', 4, 1),
       ('fish', 8, 1),
       ('potato', 6, 2),
       ('soup', 5, 2),
       ('rice', 3, 2),
       ('chicken', 8, 2);

INSERT INTO Vote(user_id, rest_id)
VALUES (2, 3),
       (3, 2);