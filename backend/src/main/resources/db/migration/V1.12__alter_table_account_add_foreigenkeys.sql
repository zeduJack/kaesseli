ALTER TABLE account
Add CONSTRAINT fk_usergroup
      FOREIGN KEY(user_usergroup_id)
      REFERENCES "userusergroup"(id);