use fisa

DROP TABLE IF EXISTS rating;

CREATE TABLE rating (
	ratingId varchar(300) PRIMARY KEY,
	movieId bigint,
	FOREIGN KEY (movieId) REFERENCES movieinfo (movieId),
	userId bigint,
	FOREIGN KEY (userId) REFERENCES user (userId),
	userRating	float(2)
	
	
)

