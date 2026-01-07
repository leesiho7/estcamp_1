package model;

public class Song_imformationVo {
private String singerName;
private String songName;
private int correctedNumber;
private int songRanking;

// Getter // Setter
public String getsingerName() {
	return singerName;
}
public void setsingerName(String singerName) {
	this.singerName = singerName;
}
public String getsongName() {
	return songName;
}
public void setsongName(String songName) {
	this.songName = songName;
}
public int getcorrectedNumber() {
	return correctedNumber;
}

public void setcorrectedNumber(int correctedNumber) {
	this.correctedNumber = correctedNumber;
}

public int getsongRanking() {
	return songRanking;
}

public void setsongRanking(int songRanking) {
	this.songRanking = songRanking;
}



// toString
@Override
public String toString() {
	return "Song_Imformation [singer name=" + singerName + ", song name=" + songName+ ", corrected number=" + correctedNumber + ", song ranking=" + songRanking + "]";
}

// 기본생성자 // 전체 멤버를 필요로하는 생성자
public Song_imformationVo (String userId, String userPw, int correctNumber, int ranking) {
	this.singerName = singerName;
	this.songName = songName;
	this.correctedNumber = correctedNumber;
	this.songRanking = songRanking;

}
}
