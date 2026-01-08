package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import jplayer.JMP3Player;
import model.Song_imformationDAO;
import model.Song_imformationVo;
import model.User_imformationVo;

public class MusicQuizGame {

	JMP3Player player = new JMP3Player();
	String path = "C:\\Users\\smhrd\\Desktop\\sorce\\";

	ArrayList<String> song = new ArrayList<>(Arrays.asList("Avicii-01-Waiting For Love.mp3",
			"BewhY (비와이)-02-Day Day (Feat. 박재범) (Prod. by GRAY).mp3", "Calvin Harris-03-Giant.mp3",
			"J. Balvin,Khalid-4-Otra Noche Sin Ti.mp3", "Jeremih-05-oui.mp3", "LUCY-06-히어로.mp3",
			"Panic! At The Disco-07-High Hopes.mp3", "pH-1-08-Good Day (Feat. 팔로알토) (Prod. 코드 쿤스트).mp3",
			"Sia-09-Unstoppable.mp3", "Tones And I-10-Dance Monkey.mp3", "김흥국-11-호랑나비.mp3", "다비치-12-미워도 사랑하니까.mp3",
			"다비치-13-안녕이라고 말하지마.mp3", "다비치-14-타임캡슐.mp3", "린다G-15-LINDA (Feat. 윤미래).mp3", "박봄-16-봄 (feat. 산다라박).mp3",
			"비-17-깡.mp3", "오마이걸-18-살짝 설렜어 (Nonstop).mp3", "이찬혁-19-멸종위기사랑.mp3", "존박-20-BLUFF.mp3", "하현상-21-시간과 흔적.mp3",
			"한로로-22-입춘.mp3", "한요한-23-불꽃 (Feat. 조현아 Of 어반자카파).mp3", "호시X우지 (SEVENTEEN)-24-STUPID IDIOT.mp3"));

	Song_imformationDAO sidao = new Song_imformationDAO();

	public void start(User_imformationVo user) {
		Scanner sc = new Scanner(System.in);

		// 🔥 핵심: 문제 리스트
		ArrayList<Song_imformationVo> quizList = sidao.selectAll();

		System.out.println("🎵 음악 퀴즈 시작!");
		System.out.println(user.getUserId() + "님 환영합니다!");

		for (int i = 0; i < song.size(); i++) {

			System.out.println((i + 1) + "번 문제!");
			player.play(path + song.get(i));

			Song_imformationVo currentVo = quizList.get(i);

			System.out.print("정답 입력 >> ");
			String answer = sc.nextLine().trim();

			String correct = currentVo.getsongName();

			if (correct != null && answer.equalsIgnoreCase(correct.trim())) {
				System.out.println("⭕ 정답");
			} else {
				System.out.println("❌ 오답");
				System.out.println("정답은 : " + correct);
			}
		}
		System.out.println("🎮 게임 종료!");
		System.out.println("메인메뉴로 돌아갑니다...");

	}
}