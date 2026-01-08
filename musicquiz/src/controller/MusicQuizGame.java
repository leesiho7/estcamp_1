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
	String path = "C:\\Users\\smhrd\\Desktop\\Java\\workspace\\Mp3player\\src\\source\\";

	ArrayList<String> song = new ArrayList<>(Arrays.asList("한로로-01-입춘.mp3", "한요한-01-불꽃 (Feat. 조현아 Of 어반자카파).mp3",
			"호시X우지 (SEVENTEEN)-03-STUPID IDIOT.mp3", "하현상-03-시간과 흔적.mp3", "임한별-01-그 길에.mp3", "이찬혁-05-멸종위기사랑.mp3",
			"우디 (Woody)-01-어제보다 슬픈 오늘.mp3", "엔플라잉 (N.Flying)-10-Flashback.mp3", "아이유-10-에필로그.mp3",
			"순순희(기태),신예영-01-결혼.mp3", "마크툽 (MAKTUB)-01-시작의 아이.mp3", "다비치-01-타임캡슐.mp3", "김동명-01-하늘 끝에서 흘린 눈물.mp3",
			"ZUTOMAYO-01-TAIDADA.mp3", "Yuuri-01-Dried Flower.mp3", "WOODZ-03-Drowning.mp3",
			"Swedish House Mafia-07-Don't You Worry Child (Feat. John Martin).mp3", "Sia-01-Unstoppable.mp3",
			"Rauw Alejandro-01-Todo De Ti.mp3", "Panic! At The Disco-03-High Hopes.mp3",
			"OFFICIAL HIGE DANDISM-01-Pretender.mp3", "LUCY-01-히어로.mp3", "J. Balvin,Khalid-25-Otra Noche Sin Ti.mp3",
			"DAY6 (데이식스)-02-한 페이지가 될 수 있게.mp3", "Cody Fry-01-Thinking About You.mp3",
			"Claire Rosinkranz-01-don't miss me.mp3", "Bad Bunny,Jhayco-01-DÁKITI (Explicit Ver.).mp3",
			"Avicii-01-Waiting For Love.mp3", "Anonymous Artists(어나니머스 아티스트)-01-퇴사 (Art. 이민석) (Prod. GC).mp3"));

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
			String answer = sc.next().trim();

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