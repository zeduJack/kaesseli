ALTER TABLE "transaction"
Add CONSTRAINT fk_user
      FOREIGN KEY(user_id)
      REFERENCES "user"(id);

ALTER TABLE "transaction"
Add CONSTRAINT fk_account
      FOREIGN KEY(account_id)
      REFERENCES "account"(id);