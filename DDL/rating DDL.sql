use fisa

DROP TABLE IF EXISTS rating;

CREATE TABLE rating (
	rating_id varchar(300) PRIMARY KEY,
	movie_id bigint,
	FOREIGN KEY (movie_id) REFERENCES movieinfo (movie_id),
	user_id bigint,
	FOREIGN KEY (user_id) REFERENCES user (user_id)	,
	user_rating	float(2)
	
	
)

