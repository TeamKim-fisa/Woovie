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

    // 생성자: MovieDAO 객체를 생성할 때 데이터베이스 연결을 초기화
    public MovieDAO() {
        try {
            con = DBUtil.getConnection(); // DBUtil로 데이터베이스 연결
        } catch (SQLException e) {
            e.printStackTrace(); // 연결 실패 시 예외 처리
        }
    }

    // 영화 이름을 받아서 영화 리스트를 검색하는 메소드 (Select)
    public List<Movie> searchMovies(String name) throws SQLException {
        List<Movie> movies = new ArrayList<>(); // 결과를 저장할 리스트 초기화
        
        // 전체 영화 정보를 선택
        StringBuilder query = new StringBuilder("SELECT * FROM movieinfo WHERE 1=1");
        
        // 이름 조건 추가
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE ?");
        }

        try {
            pstmt = con.prepareStatement(query.toString());
            int index = 1; // 쿼리 파라미터 인덱스 초기화
            
            // 이름이 주어지면 쿼리 파라미터에 설정
            if (name != null && !name.isEmpty()) {
                pstmt.setString(index++, "%" + name + "%");
            }

            rset = pstmt.executeQuery();
            while (rset.next()) {
                // 결과 집합에서 영화 정보를 추출하여 Movie 객체 생성
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
