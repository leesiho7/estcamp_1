package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import jplayer.JMP3Player;
import model.Song_imformationDAO;
import model.Song_imformationVo;
import model.User_imformationDAO;
import model.User_imformationVo;
import model.User_rankingDAO;
import view.MainGame;
import view.MainHint;
import view.MainManu;

public class MusicQuizGame {

    JMP3Player player = new JMP3Player();
    MainGame Game = new MainGame();
    MainHint Hint = new MainHint();
    MainManu Ending = new MainManu();
    String path = "C:\\Users\\smhrd_\\Desktop\\sorce\\";

    ArrayList<String> song = new ArrayList<>(Arrays.asList(
    	"존박-20-BLUFF.mp3",
    	"Tones And I-10-Dance Monkey.mp3",
    	"Calvin Harris-03-Giant.mp3",
    	"pH-1-08-Good Day (Feat. 팔로알토) (Prod. 코드 쿤스트).mp3",
    	"Panic! At The Disco-07-High Hopes.mp3",
    	"린다G-15-LINDA (Feat. 윤미래).mp3",
    	"J. Balvin,Khalid-4-Otra Noche Sin Ti.mp3",
    	"호시X우지 (SEVENTEEN)-24-STUPID IDIOT.mp3",
    	"Sia-09-Unstoppable.mp3",
        "Avicii-01-Waiting For Love.mp3",
        "Jeremih-05-oui.mp3",
        "비-17-깡.mp3",
        "이찬혁-19-멸종위기사랑.mp3",
        "다비치-12-미워도 사랑하니까.mp3",
        "박봄-16-봄 (feat. 산다라박).mp3",
        "한요한-23-불꽃 (Feat. 조현아 Of 어반자카파).mp3",
        "오마이걸-18-살짝 설렜어 (Nonstop).mp3",
        "하현상-21-시간과 흔적.mp3",
        "다비치-13-안녕이라고 말하지마.mp3",
        "한로로-22-입춘.mp3",
        "다비치-14-타임캡슐.mp3",
        "김흥국-11-호랑나비.mp3",
        "LUCY-06-히어로.mp3"
        
        
        
        
        
        
    ));

    Song_imformationDAO sidao = new Song_imformationDAO();

    // ============================
    // 게임 시작
    // ============================
    public int start(User_imformationVo user, Scanner sc) {

       

        ArrayList<Song_imformationVo> quizList = sidao.selectAll();

        int score = 0;         // 총 점수
        int correctCount = 0;  // 정답 개수
        
        // 게임시작
        
        System.out.println(user.getUserId() + "님 환영합니다!");

        for (int i = 0; i < quizList.size(); i++) {

            boolean usedHint = false;
            
            Game.showQuestion(i+1);
            player.play(path + song.get(i));

            Song_imformationVo currentVo = quizList.get(i);
            String correct = currentVo.getsongName();
            
            System.out.println("힌트를 원하면 '힌트' 입력");
            System.out.print("정답 입력 >> ");
            String answer = sc.nextLine().trim();

            if (answer.equals("힌트")) {
                usedHint = true;
                Hint.showHintIntro();
                System.out.println("💡 힌트 : 노래 제목은 '" + correct.charAt(0) + "' 로 시작합니다");
                System.out.print("정답 다시 입력 >> ");
                answer = sc.nextLine().trim();
            }

            if (correct != null && answer.equalsIgnoreCase(correct.trim())) {

                correctCount++;                       // ⭐ 정답 개수 증가
                sidao.increaseCorrectedNumber(correct); // ⭐ 노래 정답 수 +1

                if (usedHint) {
                    score += 5;
                    player.stop();
                    sound.correct();
                    Game.showTure5();
                    System.out.println("⭕ 정답! (+5점)");
                } else {
                    score += 10;
                    player.stop();
                    sound.correct();
                    Game.showTure10();
                    System.out.println("⭕ 정답! (+10점)");
                }

            } else {
            	// 오답
                Game.showFalse();
                player.stop();
                sound.wrong();
                System.out.println("정답은 : " + correct);
                
            }
            try {
                Thread.sleep(2000); // ⭐ 효과음 듣는 시간
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Game.showNext();
            sound.next();
            try {
                Thread.sleep(1500); // ⭐ 효과음 듣는 시간
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("현재 점수 : " + score + "점");
            System.out.println("---------------------------------");

            
        }

        // ============================
        // 게임 종료 처리
        // ============================

        User_imformationDAO userDao = new User_imformationDAO();
        User_rankingDAO rankingDao = new User_rankingDAO();

        // 1️⃣ 최고 점수 갱신
        boolean pointUpdated =
            userDao.updateHighPoint(user.getUserId(), score);

        // 2️⃣ 최고 정답 개수 갱신
        boolean correctUpdated =
            rankingDao.updateHighCorrectNumber(
                user.getUserId(),
                correctCount
            );

        // 3️⃣ 유저 랭킹 동기화
        rankingDao.syncFromUserInformation();

        // 4️⃣ 노래 랭킹 재계산 (⭐ 게임 종료 후 1회)
        sidao.updateSongRanking();

        System.out.println("🏆 랭킹 정보가 업데이트되었습니다.");

        // ============================
        // 종료 / 메인메뉴 선택
        // ============================
        while (true) {
//            System.out.println();
//            System.out.println("1. 메인메뉴로 돌아가기");
//            System.out.println("0. 게임 종료");
//            System.out.print("선택 >> ");
        	Game.showEnding(score, correctCount);
        	Ending.showEndingManu();
        	sound.clear();

            String input = sc.nextLine();

            if (input.equals("1")) return 1;
            if (input.equals("0")) return 0;

            System.out.println("잘못된 선택입니다.");
        }
    }
}
