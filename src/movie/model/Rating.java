package movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Rating {
	private String ratingId; //평점 고유 id
	private long movieId; //영화 고유 id
	private long userId; //사용자 id
	private long userRating; //사용자 평점
}
