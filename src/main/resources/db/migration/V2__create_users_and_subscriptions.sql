CREATE TABLE users
(
    id          UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    name        VARCHAR(255)             NOT NULL,
    email       VARCHAR(100)             NOT NULL,
    external_id VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE subscriptions
(
    id         UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    partner    VARCHAR(255)             NOT NULL,
    price      FLOAT                    NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE user_friends
(
    user_id   UUID NOT NULL,
    friend_id UUID NOT NULL,
    CONSTRAINT pk_user_friends PRIMARY KEY (user_id, friend_id),
    CONSTRAINT fk_user_friends_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_friends_friend FOREIGN KEY (friend_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_subscriptions
(
    user_id         UUID NOT NULL,
    subscription_id UUID NOT NULL,
    CONSTRAINT pk_user_subscriptions PRIMARY KEY (user_id, subscription_id),
    CONSTRAINT fk_user_subscriptions_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_subscriptions_subscription FOREIGN KEY (subscription_id) REFERENCES subscriptions (id) ON DELETE CASCADE
);

