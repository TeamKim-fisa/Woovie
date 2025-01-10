/* - select: 동일 userId 있는지 없는지 -> boolean 
 * - insert: userId, userName 
 * - delete: userId행에서 있으면 삭제 -> boolean
 */
package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class UserDAO {
	
	//select: 동일 userId 있는지 없는지 -> boolean
	public static boolean checkUserExists(long userId) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	        con = DBUtil.getConnection();
	        
	        pstmt = con.prepareStatement("SELECT COUNT(*) FROM user WHERE user_id = ?");
	        pstmt.setLong(1, userId);

	        rset = pstmt.executeQuery();
	        if (rset.next()) {
	            // COUNT(*) 결과가 1 이상이면 존재
	            return rset.getInt(1) > 0; 
	        }
	    } finally {
	        DBUtil.close(con, pstmt, rset);
	    }
	    
	    return false; //id 없으면 false, 존재하면 true
	}

	
	
	//insert: userId, userName -> boolean
	public static boolean addUser(long userId, String userName) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBUtil.getConnection();
	        
	        // 사용자 정보를 삽입하는 SQL 쿼리
	        pstmt = con.prepareStatement("INSERT INTO user (user_id, user_name) VALUES (?, ?)");
	        pstmt.setLong(1, userId);
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


}
