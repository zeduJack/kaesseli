-- transaction
CREATE TABLE transaction (
    id serial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    amount VARCHAR(40) NOT NULL,
    user_id INT NOT NULL,
    account_id INT NOT NULL,
    debit BOOLEAN NOT NULL,
    status VARCHAR(20),
    message VARCHAR(256) NOT NULL
);