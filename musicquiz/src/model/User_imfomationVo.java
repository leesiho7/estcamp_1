package model;

public class User_imfomationVo {
	private String userId;
	private String userPw;
	private int ranking;
	private int point;

// Getter // Setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPoint() {
		return point;
	}

	public void setpoint(int point) {
		this.point = point;
	}

// toString
	@Override
	public String toString() {
		return "User_Imformation [userId=" + userId + ", userPw=" + userPw + ", raking=" + raking + ", point=" + point
				+ "]";
	}

// 기본생성자 // 전체 멤버를 필요로하는 생성자
	public User_imfomationVo(String userId, String userPw, int ranking, int point) {
		this.userId = userId;
		this.userPw = userPw;
		this.ranking = ranking;
		this.point = point;
	}

	public User_imfomationVo() {
	}

}
