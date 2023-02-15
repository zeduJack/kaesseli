--user_usergroup
CREATE TABLE userusergroup (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    usergroup_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);