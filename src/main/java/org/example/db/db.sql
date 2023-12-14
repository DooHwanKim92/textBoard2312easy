CREATE DATABASE textBoard;

USE textBoard;

-------------------------- article table
CREATE TABLE article (
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title char(100) NOT NULL,
	content text NOT NULL,
	memberId int NOT NULL,
	regDate Datetime NOT null
);
-------------------------- member table
CREATE TABLE `member` (
id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
userId char(100) NOT NULL UNIQUE,
`password` char(100) NOT NULL,
regDate datetime NOT null
);

-------------------------- test member setting
INSERT INTO `member`
SET
userId = 'user1',
`password` = '1234',
regDate = NOW();

INSERT INTO `member`
SET
userId = 'user2',
`password` = '1233',
regDate = NOW();

INSERT INTO `member`
SET
userId = 'user3',
`password` = '1222',
regDate = NOW();

-------------------------- test article setting
INSERT INTO article
SET
title = 'title1',
content = 'content1',
memberId = 'member1',
localDate = NOW();

INSERT INTO article
SET
title = 'title2',
content = 'content2',
memberId = 'member2',
localDate = NOW();

INSERT INTO article
SET
title = 'title3',
content = 'content3',
memberId = 'member3',
localDate = NOW();