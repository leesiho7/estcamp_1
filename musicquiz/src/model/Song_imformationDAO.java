package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Song_imformationDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public Song_imformationDAO() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setConnection() {

		String url = "jdbc:oracle:thin:@//estcamp.site:11521/FREEPDB1";
		String id = "MP2_T3";
		String pw = "Mp2T3#2025!";

		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Song_imformationVo> selectAll() {
		ArrayList<Song_imformationVo> result = null;

		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING FROM SONG_IMFORMATION ORDER BY SINGERNAME";

		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				if (result == null) {
					result = new ArrayList<Song_imformationVo>();
				}

				String singerName = rs.getString(1);
				String songName = rs.getString(2);
				int correctedNumber = rs.getInt(3);
				int songRanking = rs.getInt(4);
				result.add(new Song_imformationVo(singerName, songName, correctedNumber, songRanking));
			}
		} catch (SQLException e) {
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
		String sql = "INSERT INTO SONG_IMFORMATION(SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING) VALUES(?, ?, ?, ?)";

		// 4. 실행 후 처리
		try {
			// psmt 준비
			psmt = conn.prepareStatement(sql);

			// 실제 값 적용
			psmt.setString(1, vo.getsingerName());
			psmt.setString(2, vo.getsongName());
			psmt.setInt(3, vo.getcorrectedNumber());
			psmt.setInt(4, vo.getsongRanking());

			// 실행
			int resultSql = psmt.executeUpdate();

			if (resultSql > 0) {
				result = true;
			}
		} catch (Exception e) {
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
		String sql = "UPDATE SONG_IMFORMATION SET CORRECTEDNUMBER = ? , SONGRANKING = ? WHERE SINGERNAME = ? ";

		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);

			// 실제 값 적용
			psmt.setInt(1, vo.getcorrectedNumber());
			psmt.setInt(2, vo.getsongRanking());
			psmt.setString(3, vo.getsingerName());

			// 실행
			int resultSql = psmt.executeUpdate();

			if (resultSql > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5. 해제
		releaseResource();

		return result;
	}

	// DELETE
	public boolean delete(int singerName) {
		boolean result = false;

		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();

		// 3. SQL 준비
		String sql = "DELETE FROM SONG_IMFORMATION WHERE SINGERNAME = ?";

		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);

			// 실제 값 적용
			psmt.setInt(1, singerName);

			// 실행
			int resultSql = psmt.executeUpdate();

			if (resultSql > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 5. 해제
		releaseResource();

		return result;
	}

	private void releaseResource() {
		// 5. 해제
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Song_imformationVo select(String songName) {
		Song_imformationVo result = null;
		// 1. 드라이버 로드 -> 생성자에서 실행
		// 2. 연결
		setConnection();
		// 3. SQL 작성 - SELECT
		String sql = "SELECT SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING FROM SONG_IMFORMATION WHERE SONGNAME = ?";

		// 4. 실행 후 처리
		try {
			psmt = conn.prepareStatement(sql);

			// 실제 값 적용
			psmt.setString(1, songName);

			// 실행
			rs = psmt.executeQuery();
			while (rs.next()) {
				String singerName = rs.getString(2);
				int correctedNumber = rs.getInt(3);
				int songRanking = rs.getInt(4);
				result = new Song_imformationVo(singerName, songName, correctedNumber, songRanking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 5. 해제
		releaseResource();
		return result;
	}

	public Song_imformationVo selectByRanking(int ranking) {

		Song_imformationVo result = null;
		setConnection();

		String sql = "SELECT SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING "
				+ "FROM SONG_IMFORMATION WHERE SONGRANKING = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ranking);
			rs = psmt.executeQuery();

			if (rs.next()) {
				result = new Song_imformationVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			releaseResource();
		}

		return result;
	}
}
