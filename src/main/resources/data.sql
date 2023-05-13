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

INSERT INTO MENU_RESTAURANT (rest_id)
VALUES (1),
       (2),
       (3),
       (4);

INSERT INTO DISH_RESTAURANT (name, price,rest_id)
VALUES ('soup',5,1),
       ('tea',2, 1),
       ('potato',6, 2),
       ('chicken',8, 2);