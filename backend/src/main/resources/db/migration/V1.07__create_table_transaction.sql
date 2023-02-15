-- transaction
CREATE TABLE transaction (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    amount DECIMAL NOT NULL,
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    debit BOOLEAN NOT NULL,
    status VARCHAR(20),
    message VARCHAR(256) NOT NULL
);