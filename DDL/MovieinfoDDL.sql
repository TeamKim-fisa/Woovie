use fisa

DROP TABLE IF EXISTS movieall;

CREATE TABLE movieall (
	name	varchar(256) PRIMARY KEY,
	rating	VARCHAR(15),
	genre	varchar(40),
	year	YEAR,
	released text,
	score	FLOAT(4,2),
	votes	DECIMAL(10,0),
	director varchar(40),
	writer	varchar(40),
	star	varchar(40),
	country	varchar(40),
	budget	FLOAT(100,2),
	gross	FLOAT(100,2),
	company	text,
	runtime DECIMAL(4,0)
)


-- truncate the table first
-- TRUNCATE TABLE movieall;
--  
-- import the file
-- LOAD DATA INFILE 'C:/Users/2-24/Downloads/movie_data.csv'
-- INTO TABLE movieall
-- FIELDS TERMINATED BY ','   -- Assuming comma-separated values
-- ENCLOSED BY '"'           -- Assuming values are enclosed in double quotes
-- LINES TERMINATED BY '\r\n'  -- Adjust according to your OS and file format
-- IGNORE 1 LINES            -- Skip the header row
-- (name, rating, genre, year, released, score, votes, director, writer, star, country, budget, gross, company, runtime);