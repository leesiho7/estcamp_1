package model;

public class Song_imformationVo {

    private String singerName;
    private String songName;
    private int correctedNumber;
    private int songRanking;

    // Getter / Setter
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

    // 기본 생성자
    public Song_imformationVo() {
    }

    // 🔥 전체 생성자 (이게 핵심)
    public Song_imformationVo(String singerName, String songName,
                              int correctedNumber, int songRanking) {
        this.singerName = singerName;
        this.songName = songName;
        this.correctedNumber = correctedNumber;
        this.songRanking = songRanking;
    }

    @Override
    public String toString() {
        return "Song_Imformation [singer name=" + singerName
                + ", song name=" + songName
                + ", corrected number=" + correctedNumber
                + ", song ranking=" + songRanking + "]";
    }
}