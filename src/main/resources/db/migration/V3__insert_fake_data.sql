INSERT INTO users (id, name, external_id, email, created_at, updated_at)
VALUES ('111e4567-e89b-12d3-a456-426614174000', 'User 1', 'user-id-1', 'user1@email.com', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('222e4567-e89b-12d3-a456-426614174001', 'User 2', 'user-id-2', 'user2@email.com', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('333e4567-e89b-12d3-a456-426614174002', 'User 3', 'user-id-3', 'user3@email.com', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('444e4567-e89b-12d3-a456-426614174003', 'User 4', 'user-id-4', 'user4@email.com', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('555e4567-e89b-12d3-a456-426614174004', 'User 5', 'user-id-5', 'user5@email.com', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

INSERT INTO user_friends (user_id, friend_id)
VALUES ('111e4567-e89b-12d3-a456-426614174000', '222e4567-e89b-12d3-a456-426614174001'),
       ('111e4567-e89b-12d3-a456-426614174000', '333e4567-e89b-12d3-a456-426614174002'),
       ('111e4567-e89b-12d3-a456-426614174000', '444e4567-e89b-12d3-a456-426614174003'),
       ('111e4567-e89b-12d3-a456-426614174000', '555e4567-e89b-12d3-a456-426614174004');

INSERT INTO user_friends (user_id, friend_id)
VALUES ('222e4567-e89b-12d3-a456-426614174001', '111e4567-e89b-12d3-a456-426614174000'),
       ('222e4567-e89b-12d3-a456-426614174001', '333e4567-e89b-12d3-a456-426614174002'),
       ('222e4567-e89b-12d3-a456-426614174001', '444e4567-e89b-12d3-a456-426614174003');

INSERT INTO user_friends (user_id, friend_id)
VALUES ('333e4567-e89b-12d3-a456-426614174002', '111e4567-e89b-12d3-a456-426614174000'),
       ('333e4567-e89b-12d3-a456-426614174002', '222e4567-e89b-12d3-a456-426614174001');

INSERT INTO user_friends (user_id, friend_id)
VALUES ('444e4567-e89b-12d3-a456-426614174003', '111e4567-e89b-12d3-a456-426614174000');

INSERT INTO subscriptions (id, partner, price, created_at, updated_at)
VALUES ('aaa14567-e89b-12d3-a456-426614174100', 'DISNEY', 100.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('bbb24567-e89b-12d3-a456-426614174101', 'NETFLIX', 200.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ccc34567-e89b-12d3-a456-426614174102', 'NETFLIX', 200.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ddd44567-e89b-12d3-a456-426614174103', 'DISNEY', 100.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('eee54567-e89b-12d3-a456-426614174104', 'SPOTIFY', 50.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO user_subscriptions (user_id, subscription_id)
VALUES ('111e4567-e89b-12d3-a456-426614174000', 'aaa14567-e89b-12d3-a456-426614174100'),
       ('111e4567-e89b-12d3-a456-426614174000', 'bbb24567-e89b-12d3-a456-426614174101'),

       ('222e4567-e89b-12d3-a456-426614174001', 'aaa14567-e89b-12d3-a456-426614174100'),
       ('222e4567-e89b-12d3-a456-426614174001', 'ccc34567-e89b-12d3-a456-426614174102'),
       ('222e4567-e89b-12d3-a456-426614174001', 'ddd44567-e89b-12d3-a456-426614174103'),

       ('333e4567-e89b-12d3-a456-426614174002', 'bbb24567-e89b-12d3-a456-426614174101'),

       ('444e4567-e89b-12d3-a456-426614174003', 'ccc34567-e89b-12d3-a456-426614174102'),
       ('444e4567-e89b-12d3-a456-426614174003', 'ddd44567-e89b-12d3-a456-426614174103'),

       ('555e4567-e89b-12d3-a456-426614174004', 'eee54567-e89b-12d3-a456-426614174104');