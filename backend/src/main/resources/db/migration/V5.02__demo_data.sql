INSERT INTO "user" (id, firstname, lastname, username, email, token, version) VALUES
(3, 'Jule', 'Schwyzer', 'juli', 'jule@mail.ch', '', 1),
(4, 'Jan', 'Schwyzer', 'jan', 'jan@mail.ch', '', 1),
(5, 'Marion', 'Schwyzer', 'marion', 'mami@mail.ch', '', 1),
(6, 'Peter', 'Schwyzer', 'peter', 'papi@mail.ch', '', 1),
(7, 'Elisabeth', 'Schwyzer', 'eli', 'omi@mail.ch', '', 1),
(8, 'Johan', 'Schwyzer', 'hans', 'opi@mail.ch', '', 1);

INSERT INTO "usergroup" (id, name, version) VALUES
(3, 'Familie Schwzyer', 1),
(4, 'Grossikässeli', 1),
(5, 'Patenkind', 1);

INSERT INTO "userusergroup" (id, usergroup_id, user_id, version) VALUES
(4,  3, 3, 1),
(5,  3, 4, 1),
(6,  3, 5, 1),
(7,  3, 6, 1),
(8,  4, 3, 1),
(9,  4, 4, 1),
(10, 4, 7, 1),
(11, 4, 8, 1),
(12, 4, 5, 1),
(13, 4, 6, 1),
(14, 5, 2, 1),
(15, 5, 5, 1);

INSERT INTO "userusergrouprole" (id, usergroup_id, user_id, role_id, version) VALUES
(4,  3, 3, 1, 1),
(5,  3, 4, 1, 1),
(6,  3, 5, 2, 1),
(7,  3, 6, 2, 1),
(8,  4, 3, 1, 1),
(9,  4, 4, 1, 1),
(10, 4, 7, 2, 1),
(11, 4, 8, 2, 1),
(12, 4, 5, 3, 1),
(13, 4, 6, 3, 1),
(14, 5, 2, 1, 1),
(15, 5, 5, 2, 1);

INSERT INTO "account" (id, saldo, type, display_name, user_usergroup_id) VALUES
(4, 87.70, 'TASCHENGELD', 'Sackgäld', 4),
(5, 47, 'TASCHENGELD', 'Sackgäld', 5),
(6, 0.10, 'TASCHENGELD', 'Kinogäld', 5),
(7, 30, 'SPAREN', 'Sparsoili', 8),
(8, 30, 'SPAREN', 'Sparbüechli', 9),
(9, 50, 'TASCHENGELD', 'Kässeli', 14);

INSERT INTO "transaction" (amount, user_id, account_id, debit, status, message, resulting_saldo) VALUES
(50, 5, 4, false, 'OK', 'Wiehnachtsgeld', 50),
(32.30, 5, 4, true, 'OK', 'Buech', 17.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 27.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 37.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 47.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 57.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 67.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 77.70),
(10, 6, 4, false, 'OK', 'Taschengeld', 87.70),
(50, 5, 5, false, 'OK', 'Wiehnachtsgeld', 50),
(8, 6, 5, false, 'OK', 'Taschengeld', 58),
(2, 6, 6, false, 'OK', 'Kinogäld', 2),
(11, 6, 6, false, 'OK', 'Fürs Kino', 13),
(11, 6, 5, true, 'OK', 'Fürs Kino', 69),
(12.90, 6, 6, true, 'OK', 'Die drei ???', 0.10),
(10, 8, 7, false, 'OK', 'Vo dGrosseletere', 10),
(10, 8, 7, false, 'OK', 'Zum Spare', 20),
(10, 8, 7, false, 'OK', 'Füers Sparsoili', 30),
(10, 8, 8, false, 'OK', 'En liebä Gruess', 10),
(10, 8, 8, false, 'OK', 'Vo dGrosseletere', 20),
(10, 8, 8, false, 'OK', 'Füers Sparbüechli', 30),
(50, 5, 9, false, 'OK', 'Zum Geburi', 50);