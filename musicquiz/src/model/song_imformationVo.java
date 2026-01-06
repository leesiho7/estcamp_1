package model;

public class song_imformationVo {
private String sigerName;
private String songName;

// Getter // Setter
public String getsigerName() {
	return sigerName;
}
public void setsigerName(String sigerName) {
	this.sigerName = sigerName;
}
public String getsongName() {
	return songName;
}
public void setsongName(String songName) {
	this.songName = songName;
}



// toString
@Override
public String toString() {
	return "Song_Imformation [siger name=" + sigerName + ", song name=" + songName + "]";
}

// 기본생성자 // 전체 멤버를 필요로하는 생성자
public song_imformationVo (String userId, String userPw) {
	this.sigerName = sigerName;
	this.songName = songName;

}
}
