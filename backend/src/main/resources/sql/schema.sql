CREATE TABLE IF NOT EXISTS messages (
    id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    text                   VARCHAR      NOT NULL
    );
TRUNCATE TABLE messages;
INSERT INTO messages(id, text) values ('id1', 'message1');
INSERT INTO messages(id, text) values ('id2', 'message2');
INSERT INTO messages(id, text) values ('id3', 'message3');