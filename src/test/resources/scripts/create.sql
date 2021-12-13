DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE person
(
	id SERIAL PRIMARY KEY,
	role_id INT,
	first_name VARCHAR(256) NOT NULL,
	last_name VARCHAR(256) NOT NULL,
	login VARCHAR(256) NOT NULL UNIQUE,
	dob TIMESTAMP NOT NULL,
	password VARCHAR(256) NOT NULL,
	email VARCHAR(256) NOT NULL,
	FOREIGN KEY (role_id) REFERENCES role (id)
);

INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('user');

INSERT INTO person (role_id, first_name, last_name, login, dob, password, email)
VALUES (1, 'Sophia', 'Denisovich', 'admin', '1987-05-28 00:00:00', 'admin', 'sophia@gmail.com');

INSERT INTO person (role_id, first_name, last_name, login, dob, password, email)
VALUES (2, 'Olesya', 'Petrova', 'user', '1987-01-04 00:00:00', 'user', 'olesya@gmail.com');
