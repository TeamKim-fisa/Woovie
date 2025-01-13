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

	// select: 동일 userName 있는지 없는지 -> boolean
	public static boolean checkUserExists(String userName) throws SQLException {
		String query = "SELECT COUNT(*) FROM user WHERE userName = ?";

		// try-with-resources를 사용하여 자원 관리
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, userName);

			try (ResultSet rset = pstmt.executeQuery()) {
				return rset.next() && rset.getInt(1) > 0;
			}
		}

	}

	// insert: userName -> boolean
	public static boolean addUser(String userName) throws SQLException {
		String query = "INSERT INTO user (userName) VALUES (?)";

		// try-with-resources를 사용하여 자원 관리
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, userName);

			int result = pstmt.executeUpdate();

			// 삽입 성공 여부 반환
			return result == 1;
		}
	}

	// delete: userId행에서 있으면 삭제 -> boolean
	public static boolean deleteUser(String userName) throws SQLException {
		boolean userExists = checkUserExists(userName); // 사용자 존재 여부 확인

		if (!userExists) {
			return false; // 존재하지 않으면 false 반환
		}

		String query = "DELETE FROM user WHERE userName = ?";

		// try-with-resources를 사용하여 자원 관리
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmtDelete = con.prepareStatement(query)) {

			pstmtDelete.setString(1, userName);

			int result = pstmtDelete.executeUpdate();

			return result == 1; // 삭제 성공 시 true 반환
		}
	}

	public static String getUserName(Long userId) throws SQLException {
		String query = "SELECT userName FROM user WHERE userId = ?";
		String returnStr = ""; // String 사용

		try (Connection con = DBUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setLong(1, userId);
			try (ResultSet rset = pstmt.executeQuery()) {
				if (rset.next()) { // 결과가 있을 때만
					returnStr = rset.getString("userName");
				} else {
					System.out.println("No data found for userId: " + userId);
				}
			}
		}

		return returnStr; // 결과가 없으면 빈 문자열 반환
	}

}