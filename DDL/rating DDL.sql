use fisa

DROP TABLE IF EXISTS rating;

CREATE TABLE rating (
	ratingID varchar(300) PRIMARY KEY,
	movieID bigint,
	FOREIGN KEY (movieID) REFERENCES movieinfo (movieID),
	userID bigint,
	FOREIGN KEY (userID) REFERENCES user (userID)	,
	user_rating	float(2)
	
	
)

