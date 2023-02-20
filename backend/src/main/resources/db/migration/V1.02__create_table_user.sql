-- user
CREATE TABLE "user" (
    id bigserial PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL DEFAULT CURRENT_TIMESTAMP,
    version serial NOT NULL,

    firstname VARCHAR(45) NOT NULL,
    lastname VARCHAR(45) NOT NULL,
    username VARCHAR(45),
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255)
);