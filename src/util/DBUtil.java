package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//모든 DAO에 모든 메소드의 공통 코드
public class DBUtil {
	private static Properties dbinfo = new Properties();
	
	//단 한번 실행 보장(그래서 위 속성 static, 외부 수정 불가하게 private)
	static {
		try {
			dbinfo.load(new FileInputStream("dbinfo.properties"));
			Class.forName(dbinfo.getProperty("jdbc.driverClassName"));
			
		} catch (Exception e) { //모든 예외의 상위 예외. 각각의 예외마다 할거면 catch 문장 여러 개!
			e.printStackTrace();
		}
	}
	
	//Connection 객체 반환하는 static 메소드 구현 
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbinfo.getProperty("jdbc.url"), dbinfo.getProperty("jdbc.username"), dbinfo.getProperty("jdbc.password"));
	}
	
	//DML용 자원반환
	//Statement를 상속받는 PreStatement
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
			}catch (SQLException e) {
				e.printStackTrace();
	}
	
}

	//DQL 자원반환
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
			}catch (SQLException e) {
				e.printStackTrace();
	}
		
	}
}

