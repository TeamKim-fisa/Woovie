/* - select: 동일 userId 있는지 없는지 -> boolean 
 * - insert: userId, userName 
 * - delete: userId행에서 있으면 삭제 -> boolean
 */
package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.model.Rating;
import movie.model.User;
import util.DBUtil;

public class UserDAO {
	
	//select: 동일 userName 있는지 없는지 -> boolean
	public static boolean checkUserExists(String userName) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	        con = DBUtil.getConnection();
	        
	        pstmt = con.prepareStatement("SELECT COUNT(*) FROM user WHERE user_name = ?");
	        pstmt.setString(1, userName);

	        rset = pstmt.executeQuery();
	        if (rset.next()) {
	            // COUNT(*) 결과가 1 이상이면 존재
	            return rset.getInt(1) > 0; 
	        }
	    } finally {
	        DBUtil.close(con, pstmt, rset);
	    }
	    
	    return false; //userName 없으면 false, 존재하면 true
	}

	
	
	//insert: userName -> boolean
	public static boolean addUser(String userName) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBUtil.getConnection();
	        
	        pstmt = con.prepareStatement("INSERT INTO user user_name VALUES ?");
	        pstmt.setString(2, userName);

	        int result = pstmt.executeUpdate();

	        // 삽입 성공 여부 반환
	        return result == 1;
	    } finally {
	        DBUtil.close(con, pstmt);
	    }
	}


	//delete: userId행에서 있으면 삭제 -> boolean
	public static boolean deleteUser(long userId) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmtCheck = null;
	    PreparedStatement pstmtDelete = null;
	    ResultSet rset = null;

	    try {
	        con = DBUtil.getConnection(); // 데이터베이스 연결

	        // UserId가 존재하는지 확인하는 쿼리
	        pstmtCheck = con.prepareStatement("SELECT COUNT(*) FROM user WHERE user_id = ?");
	        pstmtCheck.setLong(1, userId);
	        
	        rset = pstmtCheck.executeQuery();
	        if (rset.next() && rset.getInt(1) > 0) {
	            // UserId가 존재하면 삭제 진행
	            pstmtDelete = con.prepareStatement("DELETE FROM user WHERE user_id = ?");
	            pstmtDelete.setLong(1, userId);
	            pstmtDelete.executeUpdate();
	            
	            return true; // 삭제 성공 시 true
	        } else {
	            // UserId가 존재하지 않으면 false
	            return false; 
	        }
	    } finally {
	        DBUtil.close(con, pstmtCheck, rset);
	        DBUtil.close(null, pstmtDelete, null);
	    }
	}

	public static String getUserName(Long userId) throws SQLException {
	    String query = "SELECT userName FROM user WHERE userId = ?";
	    String returnStr = "";  // String 사용
	    
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(query)) {

	        pstmt.setLong(1, userId);
	        try (ResultSet rset = pstmt.executeQuery()) {
	            if (rset.next()) {  // 결과가 있을 때만
	                returnStr = rset.getString("userName");
	            }
	        }
	    }
	    
	    return returnStr;  // 결과가 없으면 빈 문자열 반환
	}

	
	
	
	
	
}