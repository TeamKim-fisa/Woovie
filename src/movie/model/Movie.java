package movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Movie {
	private long movieId; //영화 고유 id
	private String name; //영화명
	private String rating; //관람등급
	private String genre; //장르
	private String director; //감독
	private int year; //개봉연도
	private String star; //주연
	private String country; //제작국
}
