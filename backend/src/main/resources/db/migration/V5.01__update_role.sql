INSERT INTO role(id, created_at, name, updated_at, version) VALUES
(3, CURRENT_TIMESTAMP, 'OBSERVER', CURRENT_TIMESTAMP, 1);

UPDATE role
SET name = 'ADULT'
WHERE id = 2;
