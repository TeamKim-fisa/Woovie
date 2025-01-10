Use fisa

DROP TABLE IF EXISTS user;

CREATE TABLE user (
	userId	bigint PRIMARY KEY,
	userName VARCHAR(40) unique
)

