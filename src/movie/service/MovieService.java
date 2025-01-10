package movie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
	private static String[] categoryArray 
				= {"name", "genre", "director", "country"};

	/*
	 * 모든 영화 종류를 출력
	 * select * from movie;
	 * MovieDAO에 selectAllMovie가 있다고 가정
	 */
//	public static Movie allMovieinfo () {
//		return  MovieDAO.selectAllMovie();
//	}
	
	/*
	 * 사용자가 영화명, 장르 등의 카테고리를 넘겨주면,
	 * (만약 view에서 숫자로 입력받으면 int category하고, case 구분 로직 구현)
	 * 
	 * select * from movie where ? = ? 에서 ?에 들어갈 것
	 * 
	 */
	public static List<Movie> movieInfo(String category, String value) throws SQLException {
		
		// 카테고리 존재 여부 확인
//		if(categoryExist(category) == false) {	// 존재하지 않으면
//			String message =  "해당 카테고리가 존재하지 않습니다. \n" 
//				+ Arrays.toString(categoryArray)
//				+ "중 입력해주세요";
//			return Movie.errorMessage(message);
//			// Movie에 error메시지를 받는 메소드를 구현해서
//			// message를 객체로 만들어서 받음
//		}
		
		// 만약 valuExist에 null or false가 들어오면
//		if(!valueExist(category, value)) {
//			String message =  "일치하는 영화 목록이 없습니다."
//				return MovieModel.errorMessage(message);
//		}
		
		return valueExist(category, value);		// select 결과 movieModel 객체로  반환
	}
	
	
	/*
	 * 카테고리 있는지 확인
	 * 
	 */
	public static boolean categoryExist(String category) {
		// 해당 카테고리가 존재하지 않는다면?
		// 오류뜨면 해당 카테고리가 존재하지 않거나, 값이 존재하지 않습니다.
		
		for (String c : categoryArray) {
			if(c.equals(category)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	/*
	 * category가 존재할 경우
	 * 해당 값 출력
	 */
	public static List<Movie> valueExist(String category, String value) throws SQLException {
		// 먼저 MovieDAO의 selectMovie()에서
		// select * from movie where ? = ? 
		// 무조건 결과가 ResultSet에 저장 - value가 있든 없든
		// rs = MovieDAO.selectMovie(category, value);
		// DAO단에서 rs말고 model.movie에 있는 객체로 반환 받기
		List<Movie> mm = MovieDAO.searchMovies(category, value);
		
		return mm;
	}
	
	
}
