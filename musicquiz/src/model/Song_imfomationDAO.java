package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Song_imfomationDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	
	public Song_imfomationDAO() {
		
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
	
	public ArrayList<Song_imformationVo> selectAll() {
		ArrayList<Song_imformationVo> result = null;
		
		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT SIGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKIG FROM USER_IMFOMATION ORDER BY SIGERNAME";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				if (result == null) {
					result = new ArrayList<Song_imformationVo>();
				}
				
				String sigerName = rs.getString(1);
				String songName = rs.getString(2);
				int correctedNumber = rs.getInt(3);
				int songRanking = rs.getInt(4);
				result.add(new Song_imformationVo(sigerName, songName, correctedNumber, songRanking));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 5. 해제
		releaseResource();
		return result;
	}
	
	// INSERT
	public boolean insert(Song_imformationVo vo) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 작성 - INSERT
		String sql = "INSERT INTO USER_IMFOMATION(SIGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKIG) VALUES(?, ?, ?, ?)";
		
		// 4. 실행 후 처리
		try {
			// psmt 준비
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setString(1, vo.getsigerName());
			psmt.setString(2, vo.getsongName());
			psmt.setInt(3, vo.getcorrectedNumber());
			psmt.setInt(4, vo.getsongRanking());
			
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
	public boolean update(Song_imformationVo vo) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 작성 - UPDATE
		String sql = "UPDATE USER_IMFORMATION SET CORRECTEDNUMBER = ? , SONGRANKIG = ? WHERE SIGERNAME = ? ";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setInt(1, vo.getcorrectedNumber());
			psmt.setInt(2, vo.getsongRanking());
			psmt.setString(3, vo.getsigerName());
			
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
	public boolean delete(int sigerName) {
		boolean result = false;
		
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		
		// 3. SQL 준비
		String sql = "DELETE FROM USER_IMFOMATION WHERE SIGERNAME = ?";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setInt(1, sigerName);
			
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

	public Song_imformationVo select(String sigerName) {
		Song_imformationVo result = null;
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT SIGERNAME, CORRECTNUMBER, CORRECTEDNUMBER, SONGRANKIG FROM USER_IMFORMATION WHERE SIGERNAME = ?";
		
		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			
			// 실제 값 적용
			psmt.setString(1, sigerName);
			
			// 실행
			rs = psmt.executeQuery();
			while(rs.next()) {
				String songName = rs.getString(2);
				int correctedNumber = rs.getInt(3);
				int songRanking = rs.getInt(4);
				result = new Song_imformationVo(sigerName, songName, correctedNumber, songRanking);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 5. 해제
		releaseResource();
		return result;
	}
}

