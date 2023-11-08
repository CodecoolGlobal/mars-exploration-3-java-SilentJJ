--- DROP TABLES

DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS answers;

--- questions table
CREATE TABLE questions (
                           question_id SERIAL PRIMARY KEY,
                           title TEXT NOT NULL,
                           body TEXT NOT NULL,
                           number_of_likes INT,
                           created_at DATE NOT NULL
);

--- answers table
CREATE TABLE answers (
                         answer_id SERIAL PRIMARY KEY,
                         body TEXT NOT NULL,
                         number_of_likes INT,
                         question_id INT,
                         created_at DATE NOT NULL
);

INSERT INTO questions (title, body, number_of_likes, created_at) VALUES ('Help me', 'I am lost', 3, '1990-03-31');
INSERT INTO questions (title, body, number_of_likes, created_at) VALUES ('Dummy question', 'Or is it?', 5, '2020-01-01');
INSERT INTO questions (title, body, number_of_likes, created_at) VALUES ('Java', 'SAMPLE TEXT', 0, '2021-01-01');

INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Help me', 3, 1, '2021-03-01');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Help me', 3, 1, '2021-07-02');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Dummy question', 3, 2, '2021-07-01');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Dummy question', 3, 2, '2021-07-02');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Java', 3, 3, '2021-07-01');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Java', 3, 3, '2021-07-02');
INSERT INTO answers (body, number_of_likes, question_id, created_at) VALUES ('Answering Java', 3, 3, '2021-07-03');