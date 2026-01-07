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
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
//			게임시작
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("PW : ");
			String pw = sc.next();
			User_imformationVo vo = uidao.select(id,pw);
			if(vo==null) {
//				실패
				System.out.println("로그인 실패");
			} else {
//				성공
				
			}
			break;
		case 2:
//			회원가입
			System.out.print("ID : ");
			String newid = sc.next();
			System.out.print("PW : ");
			String newpw = sc.next();
			User_imformationVo vo1 = new User_imformationVo(newid,newpw, 999, 0);
			uidao.insert(vo1);
			break;
		case 3:
//			랭킹확인
			ArrayList<User_rankingVo> lists = urdao.selectAll();
			System.out.println("USER_ID\tCORRECTNUMBER\tRANKING\tPOINT");
			System.out.println("-------------------------------------------------");
			
			for(User_rankingVo vo2 : lists) {
				System.out.printf("%s \t%d\t\t%d\t%d\t\n", vo2.getUserId(), vo2.getcorrectNumber(), vo2.getRanking(), vo2.getPoint());
			}
			System.out.println("-------------------------------------------------");
			break;
		default: //종료하기
			System.out.println("종료합니다.");
			return;
		}
		
	}

	

}
