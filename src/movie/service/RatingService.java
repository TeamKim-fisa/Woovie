package movie.service;

import java.util.List;
import java.util.Optional;

import movie.dao.RatingDAO;
import movie.model.Rating;

/* AllRating과 UserRating 필요
 * 특정 영화에 대한 전체 레이팅 테이블의 정보 제공
 */
import java.util.List;
import java.util.UUID;

import movie.dao.RatingDAO;
import movie.model.Rating;

public class RatingService {
    private static final RatingService instance = new RatingService();
    private RatingService() {}

    public static RatingService getInstance() {
        return instance;
    }

    // 모든 유저가 특정 영화에 작성한 리뷰 보기
    public List<Rating> getAllRatingInfo(Long movieId) throws Exception {
        return RatingDAO.findRatingsByMovieId(movieId);
    }

    // 특정 유저가 특정 영화에 작성한 리뷰 보기
    public List<Rating> getUserRatingInfoForMovie(Long movieId, Long userId) throws Exception {
        return RatingDAO.findRatingsByUserIdAndMovieId(userId, movieId);
    }

    // 특정 유저가 작성한 모든 리뷰 보기
    public List<Rating> getAllUserRatingInfo(Long userId) throws Exception {
        return RatingDAO.findRatingsByUserId(userId);
    }

    // 특정 리뷰 정보 수정
    public String updateRatingInfo(String ratingId, Long userRating) throws Exception {
        boolean isUpdated = RatingDAO.updateRating(ratingId, userRating);
        return isUpdated ? "수정 완료" : "수정 실패";
    }

    // 특정 리뷰 정보 삭제
    public String deleteRatingInfo(String ratingId) throws Exception {
        boolean isDeleted = RatingDAO.deleteRating(ratingId);
        return isDeleted ? "삭제 완료" : "삭제 실패";
    }

    // 특정 영화에 대한 유저의 리뷰 등록
    public String registerRatingInfo(Long movieId, Long userId, Long userRating) throws Exception {
        String ratingId = UUID.randomUUID().toString();
        Rating rating = new Rating(ratingId, userRating, movieId, userId);
        boolean isRegistered = RatingDAO.registerRating(rating);
        return isRegistered ? "등록 완료" : "등록 실패";
    }
}

