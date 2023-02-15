-- scheduler
CREATE TABLE scheduler (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    schedule_time VARCHAR(40) NOT NULL,
    amount DECIMAL NOT NULL,
    message VARCHAR(256)
);
