package movie.service;

import java.sql.SQLException;

import movie.dao.UserDAO;

/*
 * 유저 생성하는 service
 * controller에서 userid, userName 받기
 * 
 */
public class UserService {

	/*
	 * 새로운 user 저장하는 로직 userid, userName 입력 받고 DAO에서 select로 userid 있는지 확인 만약
	 * userid있으면 false 반환? or String? userid 없으면 userDAO에서 insert
	 */

	public static boolean UserCreate(String userName) throws SQLException {

		// userDAO에서 select문 호출
		// 파일이름이 userDAO, 메소드이름이 getUser이라 가정
		if (!UserDAO.checkUserExists(userName)) {
			// userId가 db에 존재하지 않을 때
			// userId와 userName db에 insert
			// 파일이름이 userDAO, 메소드이름이 insertUser이라 가정
			UserDAO.addUser(userName);
			return true;

		} else {
			return false;// "존재하는 id입니다. 다른 id 입력하여 주세요.";
		}

	}

	/*
	 * 유저 정보 삭제하는 로직 delete from user where userid = ? 해당 userId가 존재하는지 확인 만약
	 * userId가 없다면? false를 받으면 삭제불가능 or 해당하는 id값 없음 userId가 있다면 정상
	 * 
	 */
	public static boolean UserDelete(String userName) throws SQLException {
		if (UserDAO.deleteUser(userName)) {
			return true;
		}
		return false;
	}
}
