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
	            		viewAllMovie();
	            		break;
	                case 2:
	                    viewMovie();
	                    break;
	                case 3:
	                    registerMovieReview();
	                    break;
	                case 4:
	                    updateMovieReview();
	                    break;
	                case 5:
	                    deleteMovieReview();
	                    break;
	                case 6:
	                    viewMovieReviews();
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
	    
	    // 모든 영화 검색 결과 보기
	    private static void viewAllMovie() {
	        System.out.print("모든 영화 리스트 ");

	        try {
	            movieController.getAllMovieInfo();
	        } catch (Exception e) {
	            System.out.println("모든 영화 검색 중 발생했습니다: " + e.getMessage());
	        }
	    }
	    
	    // 특정 영화 검색 결과 보기
	    private static void viewMovie() {
	        System.out.print("검색할 카테고리를 입력하세요(name, genre, director, country): ");
	        String category = scanner.nextLine();
	        System.out.print("검색어를 입력하세요: ");
	        String value = scanner.nextLine();
	        scanner.nextLine(); // 버퍼 비우기

	        try {
	            movieController.getMovieInfo(category, value);
	        } catch (Exception e) {
	            System.out.println("특정 영화 검색 중 발생했습니다: " + e.getMessage());
	        }
	    }
	    
	    // 영화에 리뷰 등록하기
	    private static void registerUser() {
	        System.out.print("유저 name을 입력하세요: ");
	        String username = scanner.nextLine();
	        
	        scanner.nextLine(); // 버퍼 비우기

	        try {
	            movieController.userCreate(username);
	        } catch (Exception e) {
	            System.out.println("유저 등록 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }
	    
	    
	    // 유저 삭제 하기
	    private static void deleteUser() {
	        System.out.print("삭제할 유저의 name을 입력하세요: ");
	        String userName = scanner.nextLine();

	        try {
	            movieController.deleteUser(userName);
	        } catch (Exception e) {
	            System.out.println("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }
}
