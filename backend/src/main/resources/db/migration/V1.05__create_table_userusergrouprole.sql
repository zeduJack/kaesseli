
-- user_usergroup_role
CREATE TABLE userusergrouprole (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    user_id BIGINT NOT NULL,
    usergroup_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);