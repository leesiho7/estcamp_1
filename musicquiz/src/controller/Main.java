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

		Song_imformationDAO sidao = new Song_imformationDAO();

		User_imformationDAO uidao = new User_imformationDAO();


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
	                User_imformationVo loginUser = null; //  로그인 유저 저장 변수

	                while (true) {
	                    int failCount = 0;

	                    //  로그인 시도 (최대 3번)
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
	                            loginUser = vo; 
	                            break;
	                        }
	                    }

	                    //  로그인 성공 → 게임 시작
	                    if (loginSuccess) {
	                        MusicQuizGame game = new MusicQuizGame();
	                        int result = game.start(loginUser); // ⭐ 로그인 유저 전달
	                        if (result == 0) {
	                        	System.out.println("게임을 종료합니다.");
	                        	return;   // 프로그램 종료
	                        }
	                        break; // case 1 종료 → 메인메뉴로
	                    }

	                    //  로그인 3회 실패 후 선택지
	                    System.out.println("\n로그인 횟수 초과");
	                    System.out.println("1. 다시 로그인");
	                    System.out.println("2. 회원가입");
	                    System.out.println("3. 메인메뉴");
	                    System.out.print("선택 >> ");

	                    int select = sc.nextInt();

	                    if (select == 1) {
	                        continue; // 다시 로그인
	                    } else if (select == 2) {
	                        System.out.print("ID : ");
	                        String newid = sc.next();
	                        System.out.print("PW : ");
	                        String newpw = sc.next();

	                        User_imformationVo newUser =
	                            new User_imformationVo(newid, newpw, 999, 0);
	                        uidao.insert(newUser);

	                        System.out.println("회원가입 완료! 다시 로그인하세요.");
	                    } else {
	                        break; // 메인메뉴로
	                    }
	                }
	                break;

	            case 2:
	                // 회원가입
	                System.out.print("ID : ");
	                String newid = sc.next();
	                System.out.print("PW : ");
	                String newpw = sc.next();

	                User_imformationVo vo1 =
	                    new User_imformationVo(newid, newpw, 999, 0);
	                uidao.insert(vo1);

	                System.out.println("회원가입 완료!");
	                break;

	            case 3:
	            	// ⭐ TOP 10 랭킹 확인
	            	ArrayList<User_rankingVo> lists = urdao.selectTop10();

	            	System.out.println("===== 🏆 TOP 10 RANKING 🏆 =====");
	            	System.out.println("RANK\tUSER_ID\tCORRECT\tPOINT");
	            	System.out.println("---------------------------------------");

	            	for (User_rankingVo vo2 : lists) {
	            		System.out.printf("%d\t%s\t%d\t%d\n",
	            				vo2.getRanking(),
	            				vo2.getUserId(),
	            				vo2.getcorrectNumber(),
	            				vo2.getPoint());
	            	}

	            	System.out.println("---------------------------------------");
	            	break;

	            case 0:
	                System.out.println("종료합니다.");
	                return; //  프로그램 완전 종료

	            default:
	                System.out.println("잘못된 선택입니다.");
	            }
	        }
	    }
	}
