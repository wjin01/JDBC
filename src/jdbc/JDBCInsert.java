package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCInsert {
	
	public static void main(String[] args) {
		
		/* JDBC
		 * 1. 프로그램과 Database 연결
		 * 2. 오라클에서 제공해주는 데이터베이스 연결 API
		 * 3. lib 폴더에 ojdbc11.jar를 넣고 우클릭 -> build path -> module에 ojdbc 추가
		 * 4. 자바 11버전 이후 module-info에 java.sql 패키지를 추가
		 */
		
		//1. sql 접속 정보를 선언
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR"; //계정명
		String upw = "HR"; //비밀번호
		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디>");
		String id = scan.next();
		System.out.print("비밀번호>");
		String pw = scan.next();
		System.out.print("나이>");
		int age = scan.nextInt();
		System.out.print("이메일>");
		String email = scan.next();
		
		//실행시킬SQL
		String sql = "INSERT INTO MEMBER(ID, PW, AGE, EMAIL) VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {			
			//2. JDBC드라이버 호출
			//java.sql 패키지를 사용하는데 내부 클래스를 전부 throws으로 처리되어 있어서 try~catch문 안에 사용 
			Class.forName("oracle.jdbc.OracleDriver");
			
			//3. SQL 연결을 위한 Connection객체 생성
			conn = DriverManager.getConnection(url, uid, upw); //주소, 아이디, 비밀번호
			//4. sql 쿼리구문 실행을 위한 Statment객체 생성
			//sql의 ?를 1부터 순서대로 채워줌 ( setString(), setInt(), setDate(), setTimestemp() )
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setInt(3, age);
			pstmt.setString(4, email);
			
			//5. sql실행 - insert, update, delete는 excuteUpdate()로 실행
			int result = pstmt.executeUpdate(); //적용된 로우행의 개수를 반환
			
			if(result == 1) {
				System.out.println("인서트 성공");
			} else {
				System.out.println("인서트 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		
		
	}

}
