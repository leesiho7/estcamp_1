package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Song_imformationDAO;
import model.Song_imformationVo;
import model.User_imformationDAO;
import model.User_imformationVo;
import model.User_rankingDAO;
import model.User_rankingVo;
import view.MainGame;
import view.MainLogin;
import view.MainManu;
import view.MainRanking;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		User_imformationDAO uidao = new User_imformationDAO();
		User_rankingDAO urdao = new User_rankingDAO();
		Song_imformationDAO sidao = new Song_imformationDAO();
		MainLogin loginManu = new MainLogin();
		MainRanking RankingManu = new MainRanking();
		MainManu exit = new MainManu();
		MainGame Game = new MainGame();
		
		while (true) {
			// MainLogin > LoginManu
			loginManu.showIntro();
			try {
		        Thread.sleep(3000); // ⭐ 3초 대기
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
			Game.showGame();
			try {
		        Thread.sleep(3000); // ⭐ 3초 대기
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
			loginManu.showLoginManu();

			int choice = Integer.parseInt(sc.nextLine()); // ⭐ 핵심

			switch (choice) {
			
			// =========================
			// 1️⃣ 로그인 → 바로 게임 시작
			// =========================
			case 1: {
				User_imformationVo vo = MainLogin.showLogin();
				
				User_imformationVo loginUser = uidao.select(vo.getUserId(), vo.getUserPw());
				
				if (loginUser == null) {
					System.out.println("로그인 실패");
					break;
				}

				System.out.println("로그인 성공!");
				System.out.println("게임을 시작합니다...");

				MusicQuizGame game = new MusicQuizGame();

				// ⭐ 추가 입력 없이 바로 게임 시작

				int result = game.start(loginUser, sc);

				if (result == 0) {
					System.out.println("게임을 종료합니다.");
					return;
				}
				break;
			}
			
			// MainLogin >> SIGN UP
			// =========================
			// 2️⃣ 회원가입
			// =========================
			case 2: {
				User_imformationVo voSign = MainLogin.showSignUp();

				User_imformationVo SignUser = new User_imformationVo(voSign.getUserId(),voSign.getUserPw(), 999, 0);

				uidao.insert(voSign);
				System.out.println("회원가입 완료!");
				break;
			}
			
			// =========================
			// 3️⃣ 랭킹 확인
			// =========================
			case 3: {

			    while (true) { // ⭐ 랭킹 전용 루프
			        MainRanking.showRankingManu();

			        int sel = Integer.parseInt(sc.nextLine());

			        // =========================
			        // 1️⃣ 유저 랭킹
			        // =========================
			        if (sel == 1) {
			            ArrayList<User_rankingVo> list = urdao.selectTop10();

			            System.out.println("\n===== 🏆 USER RANKING 🏆 =====");
			            System.out.println("RANK\tID\tCORRECT\tPOINT");

			            for (User_rankingVo vo : list) {
			                System.out.printf("%d\t%s\t%d\t%d\n",
			                        vo.getRanking(),
			                        vo.getUserId(),
			                        vo.getcorrectNumber(),
			                        vo.getPoint());
			            }

			            System.out.println("\n0. 뒤로가기");
			            sc.nextLine(); // ⭐ 아무 키나 누르면 랭킹 메뉴로

			        }
			        // =========================
			        // 2️⃣ 곡 랭킹
			        // =========================
			        else if (sel == 2) {
			            ArrayList<Song_imformationVo> songs = sidao.selectTop10();

			            System.out.println("\n===== 🎵 SONG RANKING 🎵 =====");
			            System.out.println("RANK  SONG                      CORRECT");

			            for (Song_imformationVo vo : songs) {
			                System.out.printf("%-5d %-25s %10d%n",
			                        vo.getsongRanking(),
			                        vo.getsongName(),
			                        vo.getcorrectedNumber());
			            }

			            System.out.println("\n0. 뒤로가기");
			            sc.nextLine(); // ⭐ 랭킹 메뉴로 복귀
			        }
			        // =========================
			        // 0️⃣ 랭킹 메뉴 나가기
			        // =========================
			        else if (sel == 0) {
			            break; // ⭐ case 3 종료 → 메인 메뉴 복귀
			        }
			        // =========================
			        // 잘못된 입력
			        // =========================
			        else {
			            System.out.println("잘못된 선택입니다.");
			        }
			    }

			    break; // ⭐ switch 탈출
			}

			

			// =========================
			// 0️⃣ 종료
			// =========================
			case 0:
				exit.showExit();
				return;

			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}
}
