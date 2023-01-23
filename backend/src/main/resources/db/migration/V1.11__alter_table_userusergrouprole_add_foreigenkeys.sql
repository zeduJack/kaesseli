ALTER TABLE userusergrouprole
Add CONSTRAINT fk_user
      FOREIGN KEY(user_id)
      REFERENCES "user"(id);

ALTER TABLE userusergrouprole
Add CONSTRAINT fk_usergroup
      FOREIGN KEY(usergroup_id)
      REFERENCES "usergroup"(id);

ALTER TABLE userusergrouprole
Add CONSTRAINT fk_role
    FOREIGN KEY(role_id)
    REFERENCES "role"(id);