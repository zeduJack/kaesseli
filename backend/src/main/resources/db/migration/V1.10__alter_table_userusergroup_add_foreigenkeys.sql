ALTER TABLE userusergroup
Add CONSTRAINT fk_user
      FOREIGN KEY(user_id)
      REFERENCES "user"(id);

ALTER TABLE userusergroup
Add CONSTRAINT fk_usergroup
      FOREIGN KEY(usergroup_id)
      REFERENCES "usergroup"(id);