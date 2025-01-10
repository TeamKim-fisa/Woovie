/* 영화명 입력 받아 영화 관련 정보 출력하는 메소드 -> select 
 */
package movie.dao;

import movie.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class MovieDAO {
    Connection con = null; 
    PreparedStatement pstmt = null; 
    ResultSet rset = null; 

    public MovieDAO() {
        try {
            con = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // 연결 실패 시 예외 처리
        }
    }

    // 영화 이름, 장르, 감독, 제작국 중에 하나의 category(칼럼명)와 value(데이터값)를 받아 영화 리스트를 검색하는 메소드(Select)
    public static List<Movie> searchMovies(String category, String value) throws SQLException {
        List<Movie> movies = new ArrayList<>(); // 결과를 저장할 리스트 초기화
        
        StringBuilder query = new StringBuilder("SELECT * FROM movieinfo WHERE 1=1");

        // 카테고리 및 값 제한 
        if (category != null && value != null && !value.isEmpty()) {
            switch (category.toLowerCase()) {
                case "name":
                    query.append(" AND name LIKE ?");
                    break;
                case "genre":
                    query.append(" AND genre = ?");
                    break;
                case "director":
                    query.append(" AND director = ?");
                    break;
                case "country":
                    query.append(" AND country = ?");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid category: " + category);
            }
        }

        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rset = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(query.toString());
            int index = 1; // 쿼리 파라미터 인덱스 초기화
            
            // 카테고리에 따라 쿼리 파라미터 설정
            if (category != null && value != null && !value.isEmpty()) {
                if (category.equalsIgnoreCase("name")) {
                    pstmt.setString(index++, "%" + value + "%");
                } else {
                    pstmt.setString(index++, value);
                }
            }

            rset = pstmt.executeQuery();
            while (rset.next()) {
                Movie movie = new Movie(
                    rset.getLong("movieId"),
                    rset.getString("name"),
                    rset.getString("rating"),
                    rset.getString("genre"),
                    rset.getString("director"),
                    rset.getInt("year"),
                    rset.getString("star"),
                    rset.getString("country")
                );
                movies.add(movie);
            }
        } finally {
            if (rset != null) rset.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }

        return movies; // 결과는 리스트 반환
    }

}
