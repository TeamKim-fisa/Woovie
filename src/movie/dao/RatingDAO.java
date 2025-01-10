package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.model.Rating;
import util.DBUtil;

public class RatingDAO {

    // 특정 영화의 모든 리뷰 가져오기
    public static List<Rating> findRatingsByMovieId(Long movieId) throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM rating WHERE movieId = ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setLong(1, movieId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    ratings.add(new Rating(
                        rset.getString("ratingId"),
                        rset.getLong("movieId"),
                        rset.getLong("userId"),
                        rset.getLong("userRating")
                    ));
                }
            }
        }
        return ratings;
    }

    // 특정 유저가 특정 영화에 작성한 리뷰 가져오기
    public static List<Rating> findRatingsByUserIdAndMovieId(Long userId, Long movieId) throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM rating WHERE userId = ? AND movieId = ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setLong(1, userId);
            pstmt.setLong(2, movieId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    ratings.add(new Rating(
                        rset.getString("ratingId"),
                        rset.getLong("userRating"),
                        rset.getLong("movieId"),
                        rset.getLong("userId")
                    ));
                }
            }
        }
        return ratings;
    }

    // 특정 유저가 작성한 모든 리뷰 가져오기
    public static List<Rating> findRatingsByUserId(Long userId) throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String query = "SELECT * FROM rating WHERE userId = ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setLong(1, userId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    ratings.add(new Rating(
                        rset.getString("ratingId"),
                        rset.getLong("userRating"),
                        rset.getLong("movieId"),
                        rset.getLong("userId")
                    ));
                }
            }
        }
        return ratings;
    }

    // 리뷰 정보 수정
    public static boolean updateRating(String ratingId, Long userRating) throws SQLException {
        String query = "UPDATE rating SET userRating = ? WHERE ratingId = ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setLong(1, userRating);
            pstmt.setString(2, ratingId);

            return pstmt.executeUpdate() > 0;
        }
    }

    // 리뷰 정보 삭제
    public static boolean deleteRating(String ratingId) throws SQLException {
        String query = "DELETE FROM rating WHERE ratingId = ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, ratingId);

            return pstmt.executeUpdate() > 0;
        }
    }

    // 리뷰 정보 등록
    public static boolean registerRating(Rating rating) throws SQLException {
        String query = "INSERT INTO rating (ratingId, movieId, userId, userRating) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, rating.getRatingId());
            pstmt.setLong(2, rating.getMovieId());
            pstmt.setLong(3, rating.getUserId());
            pstmt.setLong(4, rating.getUserRating());

            return pstmt.executeUpdate() > 0;
        }
    }
}
