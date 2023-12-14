DROP DATABASE IF EXISTS proj1;
CREATE DATABASE proj1;

USE proj1;

CREATE TABLE article (
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title char(100) NOT NULL,
	content text,
	memberId int UNSIGNED NOT NULL,
	regDate datetime NOT NULL
);

CREATE TABLE `member` (
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userId char(100) NOT NULL UNIQUE,
	`password` char(100) NOT NULL,
	regDate datetime NOT null
);

-- 근데 이 페이지는 역할이 뭐지,,