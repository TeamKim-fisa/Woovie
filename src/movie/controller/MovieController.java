package movie.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import movie.dao.MovieDAO;
import movie.dao.RatingDAO;
import movie.dao.UserDAO;
import movie.model.Movie;
import movie.model.Rating;
import movie.service.MovieService;
import movie.service.UserService;

public class MovieController {
	private final RatingDAO ratingDAO = new RatingDAO();
	private final UserDAO userDAO = new UserDAO();
	private final MovieDAO movieDao = new MovieDAO();

	// 영화에 리뷰 등록
	public boolean registerReview(Long movieId, Long userId, Long userRating) throws SQLException {
		// 리뷰를 위한 Rating 객체 생성
		Rating rating = Rating.builder().ratingId(UUID.randomUUID().toString()).userRating(userRating).movieId(movieId)
				.userId(userId).build();
		return ratingDAO.registerRating(rating);
	}

	// 특정 영화에 대한 유저의 리뷰 조회
	public void getUserReviewForMovie(Long movieId, Long userId) {
		try {
			List<Rating> ratings = ratingDAO.findRatingsByUserIdAndMovieId(userId, movieId);
			if (ratings.isEmpty()) {
				System.out.println("해당 영화에 대한 사용자의 리뷰가 없습니다.");
			} else {
				System.out.println("사용자의 리뷰:");
				for (Rating rating : ratings) {
					System.out.println(rating);
				}
			}
		} catch (SQLException e) {
			System.out.println("리뷰 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
	
	// 단일 유저가 작성한 모든 리뷰 조회
	public void getAllReviewByUser(Long userId) {
		try {
			List<Rating> ratings = ratingDAO.findRatingsByUserId(userId);
			if (ratings.isEmpty()) {
				System.out.println("유저가 작성한 리뷰가 존재하지 않습니다.");
			} else {
				System.out.println();
				String tmpUserName = userDAO.getUserName(userId);

				System.out.println("------" + tmpUserName+ "가 작성한 리뷰 리스트------");

				StringBuilder sb = new StringBuilder("");
				for (Rating rate : ratings) {
					sb.append("영화명: " + MovieDAO.findMovieNameByMovieId(rate.getMovieId()));
					sb.append("  평점: " + rate.getMovieId());
					sb.append("  리뷰ID: " + rate.getRatingId());
					sb.append("\n");
				}
				System.out.println(sb.toString());
			}
		} catch (SQLException e) {
			System.out.println("리뷰 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
	
	// 영화에 대한 모든 유저의 리뷰 조회
	public void getAllReviewsForMovie(Long movieId) {
		try {
			List<Rating> ratings = ratingDAO.findRatingsByMovieId(movieId);
			if (ratings.isEmpty()) {
				System.out.println("해당 영화에 대한 리뷰가 없습니다.");
			} else {
				System.out.println();
				System.out.println("영화에 대한 모든 리뷰:");
				for (Rating rate : ratings) {
					String tmpUserName = userDAO.getUserName(rate.getUserId());
					System.out.println("이름: " + tmpUserName + "님의 평점: " + rate.getUserRating());
				}
			}
		} catch (SQLException e) {
			System.out.println("리뷰 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	public void getAllUserMovieReview(Long userId) {
		try {
			List<Rating> userRatings = ratingDAO.findRatingsByUserId(userId);
			if (userRatings.isEmpty()) {
				System.out.println("해당 유저가 작성한 리뷰가 존재하지 않습니다.");
			} else {
				System.out.println();
				System.out.println("유저가 작성한 모든 리뷰 정보:");
				StringBuilder buildString = new StringBuilder("");
				for (Rating rate : userRatings) {
					buildString.append("평점ID: " + rate.getRatingId());
					buildString.append("영화명: " + MovieDAO.findMovieNameByMovieId(rate.getMovieId()));
					buildString.append("평점: " + rate.getUserRating());
					buildString.append("\n");
				}
				System.out.println(buildString.toString());
			}
		} catch (SQLException e) {
			System.out.println("유저의 리뷰 조회 중 예상치 못한 오류가 발생했습니다: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println();

	}

	// 특정 리뷰 수정
	public boolean updateReview(String ratingId, Long userRating) throws SQLException {
		return ratingDAO.updateRating(ratingId, userRating);
	}

	// 특정 리뷰 삭제
	public boolean deleteReview(String ratingId) throws SQLException {
		return ratingDAO.deleteRating(ratingId);
	}

	// 모든 영화 정보 조회
	public List<Movie> getAllMovieInfo() throws SQLException {
		return MovieService.allMovieInfo();
	}

	// 특정 영화 정보 조회
	public List<Movie> getMovieInfo(String category, String value) throws SQLException {
		return MovieService.movieInfo(category, value);
	}

	// 유저 등록
	public boolean userCreate(String userName) throws SQLException {
		return UserService.UserCreate(userName);
	}

	// 특정 사용자 삭제
	public boolean deleteUser(String userName) throws SQLException {
		return UserService.UserDelete(userName);
	}
}
