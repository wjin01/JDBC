package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCDelete {
	
	public static void main(String[] args) {
		
		//delete도 똑같음 - id를 입력받아서 id에 해당하는 데이터를 delete하는 jdbc 코드를 작성
		
		//1. sql 접속 정보를 선언
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR"; //계정명
		String upw = "HR"; //비밀번호
		
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디>");
		String id = scan.next();
		
		
		//sql
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver"); //드라이버 클래스 호출
			
			conn = DriverManager.getConnection(url, uid, upw); //conn 객체 생성
			
			pstmt = conn.prepareStatement(sql); //pstmt 객체 생성
			
			pstmt.setString(1, id);
			
			int result = pstmt.executeUpdate(); //insert, update, delete
			
			if(result == 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
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
