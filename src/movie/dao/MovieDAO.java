/* 영화명 입력 받아 영화 관련 정보 출력하는 메소드 -> select 
 */
package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.model.Movie;
import util.DBUtil;

public class MovieDAO {

	public static String findMovieNameByMovieId(Long movieId) throws SQLException {
		String query = "SELECT * FROM movieinfo WHERE movieId = ?";
		Movie returnMovieInfo = null;
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setLong(1, movieId);
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					returnMovieInfo = Movie.builder().movieId(rset.getLong("movieId")) // 영화 고유 ID
							.name(rset.getString("name")) // 영화명
							.rating(rset.getString("rating")) // 관람 등급
							.genre(rset.getString("genre")) // 장르
							.director(rset.getString("director"))// 감독
							.year(rset.getInt("year")) // 개봉 연도
							.star(rset.getString("star")) // 주연
							.country(rset.getString("country")) // 제작국
							.build();
				}
			}
		}
		return returnMovieInfo.getName();
	}

	// 모든 영화 정보 출력
	public static List<Movie> searchAllMovie() throws SQLException {
		String query = "SELECT * FROM movieinfo";
		List<Movie> movies = new ArrayList<>();

		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				ResultSet rset = pstmt.executeQuery()) {

			while (rset.next()) {
				movies.add(Movie.builder().movieId(rset.getLong(1)).name(rset.getString(2)).rating(rset.getString(3))
						.genre(rset.getString(4)).director(rset.getString(5)).year(rset.getInt(6))
						.star(rset.getString(7)).country(rset.getString(8)).build());
			}
		}

		return movies;
	}

	// 영화 이름, 장르, 감독, 제작국 중에 하나의 category(칼럼명)와 value(데이터값)를 받아 영화 리스트를 검색하는
	// 메소드(Select)
	public static List<Movie> searchMovies(String category, String value) throws SQLException {
		List<Movie> movies = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM movieinfo WHERE 1=1");

		// 카테고리 및 값 제한
		if (category != null && value != null && !value.isEmpty()) {
			switch (category.toLowerCase()) {
			case "name":
				query.append(" AND name LIKE ?");
				break;
			case "genre":
				query.append(" AND genre = ?");
				break;
			case "director":
				query.append(" AND director = ?");
				break;
			case "country":
				query.append(" AND country = ?");
				break;
			default:
				throw new IllegalArgumentException("Invalid category: " + category);
			}
		}

		// try-with-resources를 사용하여 자원 관리
		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query.toString())) {

			int index = 1;

			// 카테고리에 따라 쿼리 파라미터 설정
			if (category != null && value != null && !value.isEmpty()) {
				if (category.equalsIgnoreCase("name")) {
					pstmt.setString(index++, "%" + value + "%");
				} else {
					pstmt.setString(index++, value);
				}
			}

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					Movie movie = new Movie(rset.getLong("movieId"), rset.getString("name"), rset.getString("rating"),
							rset.getString("genre"), rset.getString("director"), rset.getInt("year"),
							rset.getString("star"), rset.getString("country"));
					movies.add(movie);
				}
			}
		}

		return movies;
	}

}
