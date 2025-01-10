package movie.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import movie.controller.MovieController;
import movie.model.Rating;

import movie.controller.MovieController;
import movie.model.Rating;
import movie.controller.MovieController;

public class Main {
	   private static final MovieController movieController = new MovieController();
	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            showMenu();
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // 버퍼 비우기

	            switch (choice) {
	                case 1:
	                    viewMovieReviews();
	                    break;
	                case 2:
	                    registerMovieReview();
	                    break;
	                case 3:
	                    updateMovieReview();
	                    break;
	                case 4:
	                    deleteMovieReview();
	                    break;
	                case 5:
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
	        System.out.println("1. 영화에 대한 모든 리뷰 보기");
	        System.out.println("2. 영화에 리뷰 등록");
	        System.out.println("3. 리뷰 수정");
	        System.out.println("4. 리뷰 삭제");
	        System.out.println("5. 종료");
	        System.out.print("선택: ");
	    }

	    // 특정 영화의 리뷰 보기
	    private static void viewMovieReviews() {
	        System.out.print("영화 ID를 입력하세요: ");
	        long movieId = scanner.nextLong();
	        scanner.nextLine(); // 버퍼 비우기

	        try {
	            movieController.getAllReviewsForMovie(movieId);
	        } catch (Exception e) {
	            System.out.println("리뷰 조회 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }

	    // 영화에 리뷰 등록하기
	    private static void registerMovieReview() {
	        System.out.print("영화 ID를 입력하세요: ");
	        long movieId = scanner.nextLong();

	        System.out.print("사용자 ID를 입력하세요: ");
	        long userId = scanner.nextLong();

	        System.out.print("평점을 입력하세요 (1-10): ");
	        long userRating = scanner.nextLong();
	        scanner.nextLine(); // 버퍼 비우기

	        try {
	            movieController.registerReview(movieId, userId, userRating);
	        } catch (Exception e) {
	            System.out.println("리뷰 등록 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }

	    // 리뷰 수정하기
	    private static void updateMovieReview() {
	        System.out.print("수정할 리뷰의 ratingId를 입력하세요: ");
	        String ratingId = scanner.nextLine();

	        System.out.print("새로운 평점을 입력하세요 (1-10): ");
	        long userRating = scanner.nextLong();
	        scanner.nextLine(); // 버퍼 비우기

	        try {
	            movieController.updateReview(ratingId, userRating);
	        } catch (Exception e) {
	            System.out.println("리뷰 수정 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }

	    // 리뷰 삭제하기
	    private static void deleteMovieReview() {
	        System.out.print("삭제할 리뷰의 ratingId를 입력하세요: ");
	        String ratingId = scanner.nextLine();

	        try {
	            movieController.deleteReview(ratingId);
	        } catch (Exception e) {
	            System.out.println("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }
}
