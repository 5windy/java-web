CREATE DATABASE sample;

USE sample;

CREATE TABLE users (
username VARCHAR(20) PRIMARY KEY,
`password` VARCHAR(255) NOT NULL,
email VARCHAR(40) UNIQUE,
`name` VARCHAR(40) NOT NULL,
birth DATE NOT NULL,
telecom TINYINT,
gender ENUM('male','female') NOT NULL,
country ENUM('local','foreigner') NOT NULL,
phone VARCHAR(13) NOT NULL UNIQUE,
agree BOOLEAN NOT NULL DEFAULT TRUE,
reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
mod_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY(telecom) REFERENCES telecom(`code`) ON DELETE SET NULL
);

CREATE TABLE telecom (
`code` TINYINT PRIMARY KEY,
`name` VARCHAR(20) NOT NULL
);

-- INSERT admin 계정 (1개)
INSERT INTO users(username, password, name, birth, telecom, gender, country, phone)
VALUES('admin', '1234', '김관리', '1999-09-09', 1, 'male', 'local', '010-1234-1234');

UPDATE users SET email='admin@naver.com' WHERE username='admin';

SELECT * FROM users;

-- INSERT 텔레콤 도메인
INSERT INTO telecom VALUES(1, 'SKT');
INSERT INTO telecom VALUES(2, 'KT');
INSERT INTO telecom VALUES(3, 'LGT');
INSERT INTO telecom VALUES(4, 'SKT-L');
INSERT INTO telecom VALUES(5, 'KT-L');
INSERT INTO telecom VALUES(6, 'LGT-L');


CREATE TABLE boards(
code INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(20),
title VARCHAR(255) NOT NULL,
content TEXT NOT NULL,
status ENUM('show','hide','reserv') NOT NULL DEFAULT 'show',
post_date DATETIME,
like_cnt INT DEFAULT 0,
view_cnt INT DEFAULT 0,
reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
mod_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY(username) REFERENCES users(username) ON DELETE SET NULL
) AUTO_INCREMENT=1001;

INSERT INTO boards(username, title, content) VALUES('admin','test','test content');
INSERT INTO boards(username, title, content) VALUES('admin','test1','test content1');
INSERT INTO boards(username, title, content) VALUES('admin','test2','test content2');
INSERT INTO boards(username, title, content) VALUES('admin','test3','test content3');


CREATE VIEW boards_view AS
SELECT
b.*,
u.name
FROM boards b
JOIN users u
ON b.username=u.username
WHERE b.status='show' OR b.status='reserv' AND b.post_date <= CURRENT_TIMESTAMP
ORDER BY code DESC;


SET GLOBAL event_scheduler = ON;

CREATE EVENT update_board_status
ON SCHEDULE EVERY 10 SECOND
DO
  UPDATE boards
  SET status='view'
  WHERE post_date<=NOW() AND status='reserv';