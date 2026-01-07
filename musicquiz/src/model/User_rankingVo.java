package model;

public class User_rankingVo {
	private String userId;
	private int correctNumber;
	private int ranking;
	private int point;
	

	// Getter // Setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getcorrectNumber() {
		return correctNumber;
	}
	public void setcorrectNumber(int correctNumber) {
		this.correctNumber = correctNumber;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = raking;
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
		return "User_Ranking [userId=" + userId+ ", number=" + correctNumber + ", ranking=" + raking+ ", point=" + point + "]";
	}

	// 기본생성자 // 전체 멤버를 필요로하는 생성자
	public User_rankingVo (String userId, int correctNumber, int ranking, int point) {
		this.userId = userId;
		this.correctNumber = correctNumber;
		this.ranking = ranking;
		this.point = point;
	}

	public User_rankingVo () {
	}

	}
