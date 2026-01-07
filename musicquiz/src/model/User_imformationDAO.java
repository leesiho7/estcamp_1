package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_imformationDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	
	public User_imformationDAO() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setConnection() {
		
		String url = "jdbc:oracle:thin:@//estcamp.site:11521/FREEPDB1";
		String id = "MP2_T3";
		String pw = "Mp2T3#2025!";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User_imformationVo> selectAll() {
		ArrayList<User_imformationVo> result = null;
		
		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT USER_ID, USER_PW, RANKING, POINT FROM USER_IMFORMATION ORDER BY USER_ID";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				if (result == null) {
					result = new ArrayList<User_imformationVo>();
				}
				
				String userId = rs.getString(1);
				String userPw = rs.getString(2);
				int ranking = rs.getInt(3);
				int point = rs.getInt(4);
				result.add(new User_imformationVo(userId, userPw, ranking, point));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 5. 해제
		releaseResource();
		return result;
	}
	
	// INSERT
	public boolean insert(User_imformationVo vo) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 작성 - INSERT
		String sql = "INSERT INTO USER_IMFORMATION(USER_ID, USER_PW, RANKING, POINT) VALUES(?, ?, ?, ?)";
		
		// 4. 실행 후 처리
		try {
			// psmt 준비
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserPw());
			psmt.setInt(3, vo.getRanking());
			psmt.setInt(4, vo.getPoint());
			
			// 실행
			int resultSql = psmt.executeUpdate();
			
			if (resultSql > 0) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 5. 해제
		releaseResource();
		
		return result;
	}
	
	// UPDATE
	public boolean update(User_imformationVo vo) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 작성 - UPDATE
		String sql = "UPDATE USER_IMFORMATION SET RANKING = ? , POINT = ? WHERE USER_ID = ? ";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setInt(1, vo.getRanking());
			psmt.setInt(2, vo.getPoint());
			psmt.setString(3, vo.getUserId());
			
			// 실행
			int resultSql = psmt.executeUpdate();
			
			if (resultSql > 0) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 5. 해제
		releaseResource();	
		
		return result;
	}
	
	// DELETE
	public boolean delete(int userId) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 준비
		String sql = "DELETE FROM USER_IMFORMATION WHERE USER_ID = ?";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setInt(1, userId);
			
			// 실행
			int resultSql = psmt.executeUpdate();
			
			if (resultSql > 0) {
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		// 5. 해제
		releaseResource();	
		
		return result;
	}
	
	private void releaseResource() {
		// 5. 해제
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public User_imformationVo select(String userId) {
		User_imformationVo result = null;
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT USER_ID, CORRECTNUMBER, RANKING, POINT FROM USER_IMFORMATION WHERE USER_ID = ?";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setString(1, userId);
			
			// 실행
			rs = psmt.executeQuery();
			while(rs.next()) {
				String userPw = rs.getString(2);
				int ranking = rs.getInt(3);
				int point = rs.getInt(4);
				result = new User_imformationVo(userId, userPw, ranking, point);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 5. 해제
		releaseResource();
		return result;
	}
}

