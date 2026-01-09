package model;

import java.sql.*;
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

    // =========================
    // 전체 노래 조회 (게임용)
    // =========================
    public ArrayList<Song_imformationVo> selectAll() {
        ArrayList<Song_imformationVo> list = new ArrayList<>();
        setConnection();

        String sql =
            "SELECT SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING " +
            "FROM SONG_IMFORMATION " +
            "ORDER BY SONGNAME";

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                list.add(new Song_imformationVo(
                    rs.getString("SINGERNAME"),
                    rs.getString("SONGNAME"),
                    rs.getInt("CORRECTEDNUMBER"),
                    rs.getInt("SONGRANKING")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResource();
        }
        return list;
    }

    // =========================
    // 정답 맞춘 노래 +1
    // =========================
    public void increaseCorrectedNumber(String songName) {
        setConnection();

        String sql =
            "UPDATE SONG_IMFORMATION " +
            "SET CORRECTEDNUMBER = CORRECTEDNUMBER + 1 " +
            "WHERE SONGNAME = ?";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, songName);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResource();
        }
    }

    // =========================
    // 노래 랭킹 재계산
    // =========================
    public void updateSongRanking() {
        setConnection();

        String sql =
            "UPDATE SONG_IMFORMATION s " +
            "SET SONGRANKING = ( " +
            "  SELECT rnk FROM ( " +
            "    SELECT SONGNAME, " +
            "           DENSE_RANK() OVER (ORDER BY CORRECTEDNUMBER DESC) AS rnk " +
            "    FROM SONG_IMFORMATION " +
            "  ) r " +
            "  WHERE r.SONGNAME = s.SONGNAME " +
            ")";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // ⭐ 여기서 에러 내용 꼭 확인
        } finally {
            releaseResource();
        }
    }
    

    // =========================
    // ⭐ TOP 10 노래 랭킹 조회 (핵심 수정)
    // =========================
    public ArrayList<Song_imformationVo> selectTop10() {
        ArrayList<Song_imformationVo> list = new ArrayList<>();
        setConnection();

        String sql =
            "SELECT SINGERNAME, SONGNAME, CORRECTEDNUMBER, SONGRANKING " +
            "FROM SONG_IMFORMATION " +
            "ORDER BY CORRECTEDNUMBER DESC " +
            "FETCH FIRST 10 ROWS ONLY";

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                list.add(new Song_imformationVo(
                    rs.getString("SINGERNAME"),
                    rs.getString("SONGNAME"),
                    rs.getInt("CORRECTEDNUMBER"),
                    rs.getInt("SONGRANKING")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResource();
        }
        return list;
    }

    // =========================
    // 리소스 해제
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

