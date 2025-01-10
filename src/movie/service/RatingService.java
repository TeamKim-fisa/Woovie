package movie.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* AllRating과 UserRating 필요
 * 특정 영화에 대한 전체 레이팅 테이블의 정보 제공
 */
public class RatingService {
	private static final RatingService instance = new RatingService();
	private RatingService(){}
	
	private static RatingService getInstance() {
		return instance;
	}
	// 모든 유저가 특정 영화에 작성한 리뷰 보기
	public List<Rating> getAllRatingInfo(Long movieId){
		Optional<List<Rating>> allRatingInfos = RatingDao.findRatingsByMovieId(movieId);
		if(allRatingInfos.isPresent() && ! allRatingInfos.get().isBlank()) {
			return allRatingInfos;
		}
		return null;
	}
	// 특정 유저가 특정 영화에 작성한 리뷰 보기 
	public List<Rating> getUserRatingInfoForMovie(Long movieId, Long userId){
		Optional<List<Rating>> ratingInfosForMovie = RatingDao.findRatingsByMovieId(movieId, userId);
		if(ratingInfosForMovie.isPresent() && ! ratingInfosForMovie.get().isBlank()) {
			return ratingInfosForMovie;
		}
		return null;
	}
	// 특정 유저가 작성한 모든 리뷰 데이터 출력
	public List<Rating> getAllUserRatingInfo(Long userId) {
		Optional<List<Rating>> getAllRatingInfos = RatingDao.findRatingsByMovieId(userId);
		if(getAllRatingInfos.isPresent() && ! getAllRatingInfos.get().isBlank()) {
			return getAllRatingInfos;
		}
		return null;
	}

	// 특정 리뷰 정보(ratingId)에 대한 수정 작업 진행
	public String updateRatingInfo(String ratingUUID) {
		Rating updateRatingData = Rating.builder(). id(ratingUUID)..build();
		boolean flag = RatingDao.수정함수(updateRatingData);
		if(flag) {
			return "수정 완료"
		}
		return "수정 에러";
	}

	// 특정 리뷰 정보(ratingId)에 대한 삭제 작업 진행
	public String deleteRatingInfo(String ratingUUID) {

		boolean flag = RatingDao.삭제(ratingUUID);
		if(flag) {
			return "삭제 완료"
		}
		
		return "삭제 실패";
	}

	// 특정 영화에 대한 유저의 리뷰 등록 작업 진행
	public String registerRatingInfo(Long movieId, Long userId)) {
		// uuid로 리뷰 정보에 대한 키 부여
        UUID ratingId = UUID.randomUUID();
        Rating registerData = Rating.builder()  .build();
        boolean flag = RatingDao.register(registerData);
        if(flag) {
        	return "동록 완료";
        }
        
		return "등록 실패";

	}
	
}
