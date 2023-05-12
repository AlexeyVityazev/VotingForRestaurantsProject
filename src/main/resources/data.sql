INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO DISH (name)
VALUES ('soup'),
       ('tea'),
       ('potato'),
       ('chicken'),
       ('water');

INSERT INTO RESTAURANT (name)
VALUES ('Astoria'),
       ('Italian'),
       ('Tokio City'),
       ('U Gali');

INSERT INTO MENU (id)
VALUES (1),
       (2),
       (3),
       (4);
INSERT INTO MENU_DISH (menu_id, dish_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4);