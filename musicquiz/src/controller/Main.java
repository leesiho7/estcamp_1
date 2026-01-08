package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Song_imformationDAO;
import model.User_imformationDAO;
import model.User_imformationVo;
import model.User_rankingDAO;
import model.User_rankingVo;

public class Main {

	public static void main(String[] args) {
		User_rankingDAO urdao = new User_rankingDAO();
//		ArrayList<User_rankingVo> result = urdao.selectAll();
//		System.out.println(result);
		Song_imformationDAO sidao = new Song_imformationDAO();
//		ArrayList<Song_imformationVo> result2 = sidao.selectAll();
//		System.out.println(result2);
		User_imformationDAO uidao = new User_imformationDAO();
//		ArrayList<User_imformationVo> result3 = uidao.selectAll();
//		System.out.println(result3);

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("===== 메인 메뉴 =====");
			System.out.println("1. 게임시작(로그인)");
			System.out.println("2. 회원가입");
			System.out.println("3. 랭킹확인");
			System.out.println("0. 종료");
			System.out.print("선택 >> ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				boolean loginSuccess = false;

				while (true) {
					int failCount = 0;

					// 로그인 시도 (최대 3번)
					while (failCount < 3) {
						System.out.print("ID : ");
						String id = sc.next();

						System.out.print("PW : ");
						String pw = sc.next();

						User_imformationVo vo = uidao.select(id, pw);

						if (vo == null) {
							failCount++;
							System.out.println("로그인 실패 (" + failCount + "/3)");
						} else {
							System.out.println("로그인 성공!");
							loginSuccess = true;
							break;
						}
					}

					if (loginSuccess) {
						// gameStart(vo);
						break; // 로그인 처리 종료 → 메인메뉴로
					}

					System.out.println("\n로그인 횟수 초과");
					System.out.println("1. 다시 로그인");
					System.out.println("2. 회원가입");
					System.out.println("3. 메인메뉴");

					int select = sc.nextInt();

					if (select == 1) {
						continue;
					} else if (select == 2) {
						System.out.print("ID : ");
						String newid = sc.next();
						System.out.print("PW : ");
						String newpw = sc.next();

						User_imformationVo vo1 = new User_imformationVo(newid, newpw, 999, 0);
						uidao.insert(vo1);

						System.out.println("회원가입 완료! 다시 로그인 해주세요.");
					} else {
						break; // 메인메뉴로
					}
				}
				break;

			case 2:
				System.out.print("ID : ");
				String newid = sc.next();
				System.out.print("PW : ");
				String newpw = sc.next();
				User_imformationVo vo1 = new User_imformationVo(newid, newpw, 999, 0);
				uidao.insert(vo1);
				break;

			case 3:
				ArrayList<User_rankingVo> lists = urdao.selectAll();
				System.out.println("USER_ID\tCORRECTNUMBER\tRANKING\tPOINT");
				System.out.println("-------------------------------------------------");
				for (User_rankingVo vo2 : lists) {
					System.out.printf("%s \t%d\t\t%d\t%d\n", vo2.getUserId(), vo2.getcorrectNumber(), vo2.getRanking(),
							vo2.getPoint());
				}
				System.out.println("-------------------------------------------------");
				break;

			case 0:
				System.out.println("종료합니다.");
				return; // 프로그램 완전 종료

			default:
				System.out.println("잘못된 선택입니다.");
			}
		}

	}
}
