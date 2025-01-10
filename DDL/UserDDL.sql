Use fisa

DROP TABLE IF EXISTS user;

CREATE TABLE user (
	userID	bigint PRIMARY KEY,
	userName VARCHAR(40) unique
)

