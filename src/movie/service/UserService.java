package movie.service;

import java.sql.SQLException;

import movie.dao.UserDAO;

/*
 * 유저 생성하는 service
 * controller에서 userid, userName 받기
 * 
 */
public class UserService {

	// 새로운 user 저장
	public static boolean UserCreate(String userName) throws SQLException {
		return !UserDAO.checkUserExists(userName) ? UserDAO.addUser(userName) : false;
	}

	// user 정보 삭제
	public static boolean UserDelete(String userName) throws SQLException {
		return UserDAO.deleteUser(userName) ?  true : false;
	}
}
