package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class User_rankingDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// =========================
	// 생성자 (드라이버 로드)
	// =========================
	public User_rankingDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =========================
	// DB 연결
	// =========================
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

	// ==================================================
	// 1️⃣ USER_IMFORMATION → USER_RANKING 동기화
	// (점수 기준 랭킹 계산)
	// ==================================================
	public void syncFromUserInformation() {

		setConnection();

		String sql =
			"MERGE INTO USER_RANKING r " +
			"USING ( " +
			"  SELECT USER_ID, POINT, " +
			"         RANK() OVER (ORDER BY POINT DESC) AS RANKING " +
			"  FROM USER_IMFORMATION " +
			") u " +
			"ON (r.USER_ID = u.USER_ID) " +
			"WHEN MATCHED THEN " +
			"  UPDATE SET r.POINT = u.POINT, r.RANKING = u.RANKING " +
			"WHEN NOT MATCHED THEN " +
			"  INSERT (USER_ID, POINT, RANKING, CORRECTNUMBER) " +
			"  VALUES (u.USER_ID, u.POINT, u.RANKING, 0)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		releaseResource();
	}

	// ==================================================
	// 2️⃣ 최고 정답 개수 갱신 (누적 ❌, 최고 기록만)
	// ==================================================
	public boolean updateHighCorrectNumber(String userId, int newCorrect) {

		boolean result = false;
		setConnection();

		String sql =
			"UPDATE USER_RANKING " +
			"SET CORRECTNUMBER = ? " +
			"WHERE USER_ID = ? AND CORRECTNUMBER < ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, newCorrect);
			psmt.setString(2, userId);
			psmt.setInt(3, newCorrect);

			if (psmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		releaseResource();
		return result;
	}

	// =========================
	// 3️⃣ TOP 10 랭킹 조회
	// =========================
	public ArrayList<User_rankingVo> selectTop10() {

		ArrayList<User_rankingVo> result = new ArrayList<>();
		setConnection();

		String sql =
			"SELECT USER_ID, CORRECTNUMBER, RANKING, POINT " +
			"FROM USER_RANKING " +
			"ORDER BY RANKING ASC " +
			"FETCH FIRST 10 ROWS ONLY";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result.add(new User_rankingVo(
					rs.getString("USER_ID"),
					rs.getInt("CORRECTNUMBER"),
					rs.getInt("RANKING"),
					rs.getInt("POINT")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		releaseResource();
		return result;
	}

	// =========================
	// 4️⃣ 전체 랭킹 조회
	// =========================
	public ArrayList<User_rankingVo> selectAll() {

		ArrayList<User_rankingVo> result = new ArrayList<>();
		setConnection();

		String sql =
			"SELECT USER_ID, CORRECTNUMBER, RANKING, POINT " +
			"FROM USER_RANKING ORDER BY RANKING";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				result.add(new User_rankingVo(
					rs.getString(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getInt(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		releaseResource();
		return result;
	}

	// =========================
	// 5️⃣ 단일 유저 조회
	// =========================
	public User_rankingVo select(String userId) {

		User_rankingVo vo = null;
		setConnection();

		String sql =
			"SELECT USER_ID, CORRECTNUMBER, RANKING, POINT " +
			"FROM USER_RANKING WHERE USER_ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo = new User_rankingVo(
					rs.getString(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getInt(4)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		releaseResource();
		return vo;
	}

	// =========================
	// 6️⃣ 리소스 해제
	// =========================
	private void releaseResource() {
		try {
			if (rs != null) rs.close();
			if (psmt != null) psmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
