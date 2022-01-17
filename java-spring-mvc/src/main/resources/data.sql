INSERT INTO `user` (`id`,`name`,`email`,`pass`) VALUES (1,'Aluno','aluno@email.com','$2a$10$nCclJmugDl6jQcPqLN7a6OvZDDLJGevWwQCIklxdeLO1IfyFcq5wS');
INSERT INTO `user` (`id`,`name`,`email`,`pass`) VALUES (2,'Admin','admin@email.com','$2a$10$nCclJmugDl6jQcPqLN7a6OvZDDLJGevWwQCIklxdeLO1IfyFcq5wS');

INSERT INTO `profile` (`id`,`name`,`active`) VALUES (1,'ROLE_USER','Y');
INSERT INTO `profile` (`id`,`name`,`active`) VALUES (2,'ROLE_ADMIN','Y');

INSERT INTO `rel_user_profile` (`profile_id`,`user_id`) VALUES (1,1);
INSERT INTO `rel_user_profile` (`profile_id`,`user_id`) VALUES (2,2);

INSERT INTO courses(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO courses(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topics(title, message, created_at, status, user_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);