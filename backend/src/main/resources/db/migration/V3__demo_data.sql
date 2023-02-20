INSERT INTO "user" (id, firstname, lastname, username, email, token, version) VALUES
(1, 'Max', 'Mustermann', 'max', 'mmuster@email.ch', '', 1),
(2, 'Paul', 'Mustermann', 'pauli', 'pmuster@email.ch', '', 1);


INSERT INTO "usergroup" (id, name, version) VALUES
(1, 'Mustermanns',1),
(2, 'PatentKindGroup',1);

INSERT INTO "userusergroup" (id, usergroup_id, user_id, version) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 2, 1, 1);

INSERT INTO "userusergrouprole" (id, usergroup_id, user_id, role_id, version) VALUES
(1, 1, 1, 1, 1),
(2, 1, 2, 2, 1),
(3, 1, 1, 1, 1);

INSERT INTO "account" (id, saldo, type, display_name, user_usergroup_id) VALUES
(1, 100.10, 'SPAREN', 'Sparkonto', 1),
(2, 0, 'TASCHENGELD', 'Taschengeld', 1),
(3, 50, 'SPAREN', 'Sparkonto', 2);

INSERT INTO "transaction" (amount, user_id, account_id, debit, status, message) VALUES
(100.10, 1, 1, false, 'OK', 'Weihnachtsgeld'),
(10, 1, 2, false, 'OK', 'Taschengeld 6 Nov 2022'),
(10, 1, 2, false, 'OK', 'Taschengeld 13 Nov 2022'),
(20, 1, 2, true, 'OK', 'Lego Ninjago'),
(50, 1, 3, false, 'OK', 'Weihnachtsgeld');





