package model;

public class song_raking {
	private String songName;
	private int correctedNumber;
	private int songRaking;

	// Getter // Setter
	public String songName() {
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
		return songRaking;
	}

	public void setsongRanking(int songRaking) {
		this.songRaking = songRaking;
	}

	// toString
	@Override
	public String toString() {
		return "Song_Raking [song name=" + songName + ", corrected number=" + correctedNumber + ", song raking=" + songRaking + "]";
	}

	// 기본생성자 // 전체 멤버를 필요로하는 생성자
	public song_raking (String songName, int correctNumber, int raking) {
		this.songName = songName;
		this.correctedNumber = correctedNumber;
		this.songRaking = songRaking;
	}

	public song_raking () {
	}

}
