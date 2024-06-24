package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCSelect2 {

	public static void main(String[] args) {
		
		/*
		 * 직원 테이블과 부서 테이블을 left join 하고 
		 * 
		 * 직원 아이디, 직무 아이디, 부서명, 이름만 출력
		 * 
		 * 조건은 직원아이디를 입력받아서 이 아이디에 해당하는 데이터만 출력
		 */
		
		//1. sql 접속 정보를 선언
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "HR"; //계정명
		String upw = "HR"; //비밀번호
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("직원 아이디 입력 : ");
		String id = scan.next();
		
		String sql = "SELECT e.employee_id, e.job_id, d.department_name, e.first_name\r\n"
				+ "FROM EMPLOYEES E\r\n"
				+ "LEFT JOIN DEPARTMENTS D\r\n"
				+ "ON E.department_id = d.department_id WHERE e.employee_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select는 결과를 조회해서 처리할 ResultSet 객체가 필요함
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url, uid, upw); //conn객체
			
			pstmt = conn.prepareStatement(sql); //pstmt객체
			pstmt.setString(1, id); //?값 채움
			
			rs = pstmt.executeQuery(); //select는 executeQuery() 구문으로 실행함
			
			//rs에 저장된 데이터를 1행씩 처리하는 구문
			
			while(rs.next()) { //다음행이 있는지 확인해서 다음이 있으면 전진 true 반환
				
				//1행에 대한 프로그램 처리
				//getString(), getInt(), getDate(), getTimestamp() 등을 이용해서 데이터를 읽어드림
				String ei = rs.getString("employee_id"); //id컬럼 조회
				String ji = rs.getString("job_id");
				String dn = rs.getString("department_name");
				String fn = rs.getString("first_name"); //읽은 컬럼명이 적힘
				
				System.out.print(ei + ", ");
				System.out.print(ji + ", ");
				System.out.print(dn + ", ");
				System.out.print(fn);			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
			}
		}
		
	}
}
