
-- user_usergroup_role
CREATE TABLE userusergrouprole (
    id serial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    user_id INT NOT NULL,
    usergroup_id INT NOT NULL,
    role_id INT NOT NULL
);