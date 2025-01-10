package movie.service;

/*
 * 유저 생성하는 service
 * controller에서 userid, userName 받기
 * 
 */
public class UserService {
	
	/*
	 *  새로운 user 저장하는 로직
	 *  userid, userName 입력 받고
	 *  DAO에서 select로 userid 있는지 확인
	 *  만약 userid있으면 false 반환? or String?
	 *  userid 없으면 userDAO에서 insert
	 */
	
	public static boolean UserCreate(String userId, String userName) {
		
		// userDAO에서 select문 호출
		// 파일이름이 userDAO, 메소드이름이 getUser이라 가정
		if(userDAO.getUser(userId) == false) {	//
			return false;//"존재하는 id입니다. 다른 id 입력하여 주세요.";
		}
		else {
			// userId가 db에 존재하지 않을 때
			// userId와 userName db에 insert
			// 파일이름이 userDAO, 메소드이름이 insertUser이라 가정
			userDAO.insertUser(userId, userName);
		}
		return true;
	}
	
	public static boolean UserDelete(String userId, String userName) {
		
		
		return true;
	}
}
