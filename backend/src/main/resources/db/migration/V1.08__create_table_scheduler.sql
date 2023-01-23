-- scheduler
CREATE TABLE scheduler (
    id serial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    user_id INT NOT NULL,
    account_id INT NOT NULL,
    schedule_time VARCHAR(40) NOT NULL,
    amount INT NOT NULL,
    message VARCHAR(256)
);
