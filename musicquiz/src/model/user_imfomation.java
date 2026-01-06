package model;

public class user_imfomation {
private String userId;
private String userPw;
private int raking;
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
	return "User_Imformation [userId=" + userId + ", userPw=" + userPw + ", raking=" + raking+ ", point=" + point + "]";
}

// 기본생성자 // 전체 멤버를 필요로하는 생성자
public user_imfomation (String userId, String userPw, int raking, int point) {
	this.userId = userId;
	this.userPw = userPw;
	this.raking = raking;
	this.point = point;
}

public user_imfomation () {
}

}
