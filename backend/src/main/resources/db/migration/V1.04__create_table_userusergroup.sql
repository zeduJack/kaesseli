--user_usergroup
CREATE TABLE userusergroup (
    id serial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version bigserial NOT NULL,

    usergroup_id INT NOT NULL,
    user_id INT NOT NULL
);