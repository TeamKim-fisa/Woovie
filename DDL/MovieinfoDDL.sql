use fisa

DROP TABLE IF EXISTS movieinfo;

CREATE TABLE movieinfo (
	movie_id bigint PRIMARY KEY,
	name	varchar(256),
	rating	VARCHAR(15),
	genre	varchar(40),
	director varchar(40),
	year	YEAR,
	runtime DECIMAL(4,0),
	star	varchar(40),
	country	varchar(40)
);

-- 	released text,
-- 	score	FLOAT(4,2),
-- 	votes	DECIMAL(10,0),
-- 	writer	varchar(40),
-- 	budget	FLOAT(100,2),
-- 	gross	FLOAT(100,2),
-- 	company	text,
-- 	runtime DECIMAL(4,0)
