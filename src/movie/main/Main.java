package movie.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import movie.controller.MovieController;
import movie.model.Movie;
import movie.model.Rating;
import movie.service.MovieService;
import movie.controller.MovieController;
import movie.model.Rating;
import movie.controller.MovieController;

public class Main {
	private static final MovieController movieController = new MovieController();
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		while (true) {
			showMenu();
			int choice = Integer.parseInt(br.readLine());

			switch (choice) {
			case 1:
				viewAllMovie();
				break;
			case 2:
				viewMovie();
				break;
			case 3:
				viewMovieReviews();
				break;
			case 4:
				registerMovieReview();
				break;
				
			case 5:
				updateMovieReview();
				break;
			case 6:
				deleteMovieReview();
				break;
			case 7:
				registerUser();
				break;
			case 8:
				deleteUser();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return; // 프로그램 종료
			default:
				System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
			}
		}
	}

	// 메뉴 출력
	private static void showMenu() {
		System.out.println("\n--- 영화 리뷰 시스템 ---");
		System.out.println("1. 모든 영화 검색");
		System.out.println("2. 검색어를 통한 영화 검색");
		System.out.println("3. 영화에 대한 모든 리뷰 보기");
		System.out.println("4. 영화에 리뷰 등록");
		System.out.println("5. 리뷰 수정");
		System.out.println("6. 리뷰 삭제");
		System.out.println("7. 유저 등록");
		System.out.println("8. 유저 삭제");

		System.out.println("9. 종료");
		System.out.print("선택: ");
	}

	// 특정 영화의 리뷰 보기
	private static void viewMovieReviews() throws IOException {
		System.out.print("영화 ID를 입력하세요: ");
		long movieId = Long.parseLong(br.readLine());

		try {
			movieController.getAllReviewsForMovie(movieId);
		} catch (Exception e) {
			System.out.println("리뷰 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 영화에 리뷰 등록하기
	private static void registerMovieReview() throws IOException {
		System.out.print("영화 ID를 입력하세요: ");
		long movieId = Long.parseLong(br.readLine());

		System.out.print("사용자 ID를 입력하세요: ");
		long userId = Long.parseLong(br.readLine());

		System.out.print("평점을 입력하세요 (1-10): ");
		long userRating = Long.parseLong(br.readLine());

		try {
			if(movieController.registerReview(movieId, userId, userRating)) {
				System.out.println("리뷰가 성공적으로 등록되었습니다.");
			} else {
				System.out.println("리뷰 등록에 실패했습니다.");
			}

		} catch (Exception e) {
			System.out.println("리뷰 등록 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 리뷰 수정하기
	private static void updateMovieReview() throws IOException {
		System.out.print("유저 ID를 입력해주세요");
		String userId = br.readLine();
		movieController.getAllReviewByUser(Long.parseLong(userId));
		
		System.out.print("수정할 리뷰의 리뷰ID를 입력하세요: ");
		String ratingId = br.readLine();
		
		System.out.print("새로운 평점을 입력하세요 (1-10): ");
		long userRating = Long.parseLong(br.readLine());

		try {
			if(movieController.updateReview(ratingId, userRating)) {
				System.out.println("리뷰가 성공적으로 수정되었습니다.");
			}
			else {
				System.out.println("리뷰 수정에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println("리뷰 수정 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 리뷰 삭제하기
	private static void deleteMovieReview() throws IOException {
		System.out.print("유저 ID를 입력해주세요");
		String userId = br.readLine();
		movieController.getAllReviewByUser(Long.parseLong(userId));
		
		System.out.print("삭제할 리뷰의 리뷰ID를 입력하세요: ");
		String ratingId = br.readLine();
		
		try {
			if(movieController.deleteReview(ratingId)) {
				System.out.println("리뷰가 성공적으로 삭제되었습니다.");
			}
			else {
				System.out.println("리뷰 삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// 모든 영화 검색 결과 보기
	private static void viewAllMovie() {
		System.out.print("모든 영화 리스트 ");

		try {
			List<Movie> movies = movieController.getAllMovieInfo();;
			if(movies == null || movies.isEmpty()) {
				System.out.println("영화 리스트가 없습니다.");
			}
			else {
				for (Movie m : movies) {
                    System.out.println(m.toString());
                }
			}
		} catch (Exception e) {
			System.out.println("모든 영화 검색 중 발생했습니다: " + e.getMessage());
		}
	}

	// 특정 영화 검색 결과 보기
	private static void viewMovie() throws IOException {
		System.out.print("검색할 카테고리를 입력하세요(name, genre, director, country): ");
		String category = br.readLine();
		System.out.print("검색어를 입력하세요: ");
		String value = br.readLine();

		try {
			List<Movie> movies = movieController.getMovieInfo(category, value);
			if(movies == null || movies.isEmpty()) {
				System.out.println("검색어에 맞는 영화가 없습니다.");
			}
			else {
				for (Movie m : movies) {
                    System.out.println(m.toString());
                }
			}
		} catch (Exception e) {
			System.out.println("특정 영화 검색 중 발생했습니다: " + e.getMessage());
		}
	}

	// user 등록하기
	private static void registerUser() throws IOException {
		System.out.print("유저 name을 입력하세요: ");
		String username = br.readLine();

		try {
			if (movieController.userCreate(username)) {
				System.out.println("유저가 성공적으로 등록되었습니다.");
			} else {
				System.out.println("유저 등록에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println("유저 등록 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	// user 삭제 하기
	private static void deleteUser() throws IOException {
		System.out.print("삭제할 유저의 name을 입력하세요: ");
		String userName = br.readLine();

		try {
			if(movieController.deleteUser(userName)) {
				System.out.println(userName + "님의 정보가 성공적으로 삭제되었습니다.");
			}
			else {
				 System.out.println("유저 삭제에 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println("유저 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
}
