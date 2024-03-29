-- account
CREATE TABLE account (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    type VARCHAR(40) NOT NULL,
    saldo DECIMAL NOT NULL,
    display_name VARCHAR(256) NOT NULL,
    user_usergroup_id BIGINT NOT NULL
);