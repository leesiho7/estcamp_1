package model;

public class user_ranking {
	private String userId;
	private int correctNumber;
	private int raking;
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
		return raking;
	}
	public void setRanking(int raking) {
		this.raking = raking;
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
		return "User_Ranking [userId=" + userId+ ", number=" + correctNumber + ", raking=" + raking+ ", point=" + point + "]";
	}

	// 기본생성자 // 전체 멤버를 필요로하는 생성자
	public user_ranking (String userId, int correctNumber, int raking, int point) {
		this.userId = userId;
		this.correctNumber = correctNumber;
		this.raking = raking;
		this.point = point;
	}

	public user_ranking () {
	}

	}
