INSERT INTO `user`(name, email, pass) VALUES('Aluno', 'aluno@email.com', '123456');

INSERT INTO courses(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO courses(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);