-- usergroup
CREATE TABLE usergroup (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    name VARCHAR(255) UNIQUE NOT NULL
);

-- user
CREATE TABLE "user" (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    firstname VARCHAR(45) NOT NULL,
    lastname VARCHAR(45) NOT NULL,
    username VARCHAR(45),
    email VARCHAR(255) NOT NULL
);

--user_usergroup
CREATE TABLE userusergroup (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    usergroup_id INT NOT NULL,
    user_id INT NOT NULL
);

-- role
CREATE TABLE role (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    name VARCHAR(35) NOT NULL
);

-- user_usergroup_role
CREATE TABLE userusergrouprole (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    user_id INT NOT NULL,
    usergroup_id INT NOT NULL,
    role_id INT NOT NULL
);

-- account
CREATE TABLE account (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    type VARCHAR(40) NOT NULL,
    saldo INT NOT NULL,
    displayName VARCHAR(256) NOT NULL,
    user_usergroup_id INT NOT NULL
);

-- transaction
CREATE TABLE transaction (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    amount VARCHAR(40) NOT NULL,
    user_id INT NOT NULL,
    account_id INT NOT NULL,
    debit INT NOT NULL,
    status VARCHAR(20),
    message VARCHAR(256) NOT NULL
);

-- scheduler
CREATE TABLE scheduler (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    user_id INT NOT NULL,
    account_id INT NOT NULL,
    schedule_time VARCHAR(40) NOT NULL,
    amount INT NOT NULL,
    message VARCHAR(256)
);
