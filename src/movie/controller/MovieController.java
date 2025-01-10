package movie.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import movie.dao.RatingDAO;
import movie.model.Rating;

public class MovieController {
    private final RatingDAO ratingDAO = new RatingDAO();
    
    // 영화에 리뷰 등록
    public void registerReview(Long movieId, Long userId, Long userRating) {
        try {
            // 리뷰를 위한 Rating 객체 생성
            Rating rating = new Rating(UUID.randomUUID().toString(), userRating, movieId, userId);
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
                for (Rating rating : ratings) {
                    System.out.println(rating);
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
}