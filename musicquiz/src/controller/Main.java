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

		boolean isFirst = true;

		while (true) {
			// MainLogin > LoginManu
			if (isFirst) {
				loginManu.showIntro();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Game.showGame();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				isFirst = false; // ⭐ 다시는 실행 안 됨
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

				User_imformationVo SignUser = new User_imformationVo(voSign.getUserId(), voSign.getUserPw(), 999, 0);

				uidao.insert(voSign);
				System.out.println("회원가입 완료!");
				break;
			}

			// =========================
			// 3️⃣ 랭킹 확인
			// =========================
			case 3: {

				boolean goMainMenu = false; // ⭐ 메인메뉴 이동 여부

				while (true) { // ⭐ 랭킹 전용 루프
					MainRanking.showRankingManu();

					int sel;
					try {
						sel = Integer.parseInt(sc.nextLine());
					} catch (Exception e) {
						System.out.println("숫자를 입력하세요.");
						continue;
					}

					// =========================
					// 1️⃣ 유저 랭킹
					// =========================
					if (sel == 1) {

						ArrayList<User_rankingVo> list = urdao.selectTop10();

//						System.out.println("\n===== 🏆 USER RANKING 🏆 =====");
//						System.out.println("RANK\tID\tCORRECT\tPOINT");
//
//						for (User_rankingVo vo : list) {
//							System.out.printf("%d\t%s\t%d\t%d\n", vo.getRanking(), vo.getUserId(),
//									vo.getcorrectNumber(), vo.getPoint());
//						}

						// ⭐ 유저랭킹 하위 메뉴
						while (true) {
							System.out.println();
							RankingManu.showUserRanking(list);
							
							String input = sc.nextLine();

							if (input.equals("1")) {
								goMainMenu = true;
								break;
							}

							if (input.equals("0")) {
								break;
							}

							System.out.println("잘못된 입력입니다.");
						}
					}

					// =========================
					// 2️⃣ 곡 랭킹
					// =========================
					else if (sel == 2) {

						ArrayList<Song_imformationVo> songs = sidao.selectTop10();

						RankingManu.showSongRanking(songs);

						// ⭐ 곡랭킹 하위 메뉴
						while (true) {
							String input = sc.nextLine();

							if (input.equals("1")) {
								goMainMenu = true;
								break;
							}

							if (input.equals("0")) {
								break;
							}

							System.out.println("잘못된 입력입니다.");
						}
					}

					// =========================
					// 0️⃣ 랭킹 메뉴 종료
					// =========================
					else if (sel == 0) {
						break; // ⭐ 메인 메뉴로 복귀
					}

					else {
						System.out.println("잘못된 선택입니다.");
					}

					// ⭐ 메인메뉴 이동 신호 감지
					if (goMainMenu) {
						break;
					}
				}

				break; // ⭐ switch 종료 → 메인 메뉴 while로 복귀
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
