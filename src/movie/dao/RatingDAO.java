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
    // select: ratingId, userRating
    public List<Rating> searchRating(String ratingId, Long userRating) throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        
        // 쿼리: ratingId와 userRating에 대한 조건을 사용
        StringBuilder query = new StringBuilder("SELECT * FROM rating WHERE 1=1");
        
        if (ratingId != null && !ratingId.isEmpty()) {
            query.append(" AND ratingId = ?");
        }
        if (userRating != null) {
            query.append(" AND userRating = ?");
        }
        
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rset = null;

        try {
            con = DBUtil.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(query.toString());
            int index = 1; // 쿼리 파라미터 인덱스 초기화
            
            // ratingId가 주어지면 쿼리 파라미터에 설정
            if (ratingId != null && !ratingId.isEmpty()) {
                pstmt.setString(index++, ratingId);
            }
            // userRating이 주어지면 쿼리 파라미터에 설정
            if (userRating != null) {
                pstmt.setLong(index++, userRating);
            }

            rset = pstmt.executeQuery(); // 쿼리 실행
            while (rset.next()) {
                // 결과 집합에서 Rating 정보를 추출하여 Rating 객체 생성
                Rating rating = new Rating(
                    rset.getString("ratingId"),
                    rset.getLong("userRating"),
                    rset.getLong("movieId"),
                    rset.getLong("userId")
                );
                ratings.add(rating); // 리스트에 추가
            }
        } finally {
            if (rset != null) rset.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }

        return ratings; // 결과 리스트 반환
    }

    // insert: ratingId, movieId, userId, userRating
    public static boolean addRating(String ratingId, Long movieId, Long userId, Long userRating) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("INSERT INTO rating (ratingId, movieId, userId, userRating) VALUES (?, ?, ?, ?)");
            
            pstmt.setString(1, ratingId);
            pstmt.setLong(2, movieId);
            pstmt.setLong(3, userId);
            pstmt.setLong(4, userRating);

            int result = pstmt.executeUpdate();

            return result == 1; // 삽입 성공 시 true 반환
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }

    // update: userId, ratingId, userRating
    public static boolean updateRating(Long userId, String ratingId, Long userRating) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("UPDATE rating SET userRating = ? WHERE userId = ? AND ratingId = ?");
            
            pstmt.setLong(1, userRating);
            pstmt.setLong(2, userId);
            pstmt.setString(3, ratingId);

            int result = pstmt.executeUpdate();

            return result == 1; // 업데이트 성공 시 true 반환
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }

    // delete: userId
    public static boolean deleteRating(Long userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("DELETE FROM rating WHERE userId = ?");
            
            pstmt.setLong(1, userId);

            int result = pstmt.executeUpdate();

            return result > 0; // 삭제 성공 시 true 반환
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}