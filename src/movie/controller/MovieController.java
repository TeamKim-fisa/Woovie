package movie.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import movie.dao.RatingDAO;
import movie.dao.UserDAO;
import movie.model.Movie;
import movie.model.Rating;
import movie.model.User;
import movie.service.MovieService;
import movie.service.UserService;

public class MovieController {
	private final RatingDAO ratingDAO = new RatingDAO();
	private final UserDAO userDAO = new UserDAO();

	// 영화에 리뷰 등록
	public void registerReview(Long movieId, Long userId, Long userRating) {
		try {
			// 리뷰를 위한 Rating 객체 생성
			Rating rating = Rating.builder().ratingId(UUID.randomUUID().toString()).userRating(userRating)
					.movieId(movieId).userId(userId).build();
			boolean result = ratingDAO.registerRating(rating);
			if (result) {
				System.out.println("리뷰가 성공적으로 등록되었습니다.");
			} else {
				System.out.println("리뷰 등록에 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("리뷰 등록 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 영화에 대한 특정 유저의 리뷰 조회
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

	// 영화에 대한 모든 유저의 리뷰 조회
	public void getAllReviewsForMovie(Long movieId) {
		try {
			List<Rating> ratings = ratingDAO.findRatingsByMovieId(movieId);
			if (ratings.isEmpty()) {
				System.out.println("해당 영화에 대한 리뷰가 없습니다.");
			} else {
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

	// 특정 리뷰 수정
	public void updateReview(String ratingId, Long userRating) {
		try {
			boolean result = ratingDAO.updateRating(ratingId, userRating);
			if (result) {
				System.out.println("리뷰가 성공적으로 수정되었습니다.");
			} else {
				System.out.println("리뷰 수정에 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("리뷰 수정 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 특정 리뷰 삭제
	public void deleteReview(String ratingId) {
		try {
			boolean result = ratingDAO.deleteRating(ratingId);
			if (result) {
				System.out.println("리뷰가 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("리뷰 삭제에 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 모든 영화 정보 조회
	public void getAllMovieInfo() {
		try {
			List<Movie> movies = MovieService.allMovieInfo();
			if (movies.isEmpty()) {
				System.out.println("영화 리스트가 없습니다.");
			} else {
				for (Movie m : movies) {
					System.out.println(m.toString());
				}
			}
		} catch (SQLException e) {
			System.out.println("영화 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 특정 영화 정보 조회
	public void getMovieInfo(String category, String value) {
		try {
			List<Movie> movies = MovieService.movieInfo(category, value);
			if (movies.isEmpty()) {
				System.out.println("검색어에 맞는 영화가 없습니다.");
			} else {
				for (Movie m : movies) {
					System.out.println(m.toString());
				}
			}
		} catch (SQLException e) {
			System.out.println("특정 영화 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 유저 등록
	public void userCreate(String userName) {
		try {
			// 유저 등록을 위한 객체 생성
//			User user = User.builder().userName(userName).build();
			boolean result = UserService.UserCreate(userName);
			if (result) {
				System.out.println("유저가 성공적으로 등록되었습니다.");
			} else {
				System.out.println("유저 등록에 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("유저 등록 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 특정 사용자 삭제
	public void deleteUser(String userName) {
		try {
			boolean result = UserService.UserDelete(userName);
			if (result) {
				System.out.println(userName + "님의 정보가 성공적으로 삭제되었습니다.");
			} else {
				System.out.println("유저 삭제에 실패했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("유저 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
}