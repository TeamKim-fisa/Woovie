package movie.service;

import java.sql.SQLException;
import java.util.List;

import movie.dao.MovieDAO;
import movie.model.Movie;

/*
 * 영화 정보 출력
 * 특정 영화 출력 가능 데이터
 * - 영화명
 * - 장르
 * - 감독
 * - 제작국
 * - ...
 * 
 * 출력 값 갯수 제한
 */
public class MovieService {
//	private static String[] categoryArray 
//				= {"name", "genre", "director", "country"};

	// 모든 영화 출력
	public static List<Movie> allMovieInfo () throws SQLException {
		return MovieDAO.searchAllMovie();
	}
	
	// 특정 영화 출력
	public static List<Movie> movieInfo(String category, String value) throws SQLException {
		return MovieDAO.searchMovies(category, value);	// select 결과 movieModel 객체로  반환
	}
}
