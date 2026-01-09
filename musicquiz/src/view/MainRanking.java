package view;

import java.util.ArrayList;

import model.Song_imformationVo;
import model.User_rankingVo;

public class MainRanking {
	
	public static void showRankingIntro() {
		// 랭킹 인트로
		System.out.println("                        ,;,                    \r\n"
				+ "                        ~@:                             \r\n"
				+ "                       .:$;                             \r\n"
				+ "                     $$$!,!$$=.                         \r\n"
				+ "                     ;$,   ,$;                          \r\n"
				+ "            :;        -#~ ~$-                           \r\n"
				+ "           -$!.       ,=...!-                           \r\n"
				+ "         ,,:*;;,,     ,$!$!$-         ..                \r\n"
				+ "        .@=*:~=$@,    ,=: ~*-         ;=,               \r\n"
				+ "        .!:,  ,!*.                   -;=-               \r\n"
				+ "          !!  ;!.  ;;;;;;;;;;;;;  .;;=;;!;;,            \r\n"
				+ "          ;;;:!:  .$:::::::::::#  ,$$:,,~$@,            \r\n"
				+ "         .#=;;$*, .=           $   -;~  ;*~             \r\n"
				+ "          :-..~-  .=     ..    $    :!,.;:              \r\n"
				+ "      .,,,,,,,,,,,-=    .!:    $    :=$$:=.             \r\n"
				+ "      $=*==**===**=$    :#;    $   ,$=, =@.             \r\n"
				+ "      #           .=     :;    $.                       \r\n"
				+ "      #     .,    .=     :;    $$$$$$$$$$$$$#           \r\n"
				+ "      #    -$$;   .=     ::    $            @           \r\n"
				+ "      #    . ;;   .=     ..    $    .~~.    @           \r\n"
				+ "      #      !-   .=           $    -  ~    @           \r\n"
				+ "      #    ,*:    .=           $      =~    @           \r\n"
				+ "      #    :$     .=           $      *;.   @           \r\n"
				+ "      #    ,--.   .=           $    -  !.   @           \r\n"
				+ "      #           .=           $    ,!!,    @           \r\n"
				+ "      #           .=           $            @           \r\n"
				+ " -$@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@$-     \r\n"
				+ "                                                         ");
		
	}
	
	public static void showRankingManu() {
		// 랭킹 메뉴
		System.out.println("==================================================\r\n"
						+ "              ★ MUSIC QUIZ RANKING ★\r\n"
						 + "==================================================");
		System.out.println("┌────────────────────────────────────────────────┐");
	    System.out.println("│               [ RANKING  MENU ]                │");
		System.out.println("├────────────────────────────────────────────────┤");
		System.out.println("│               1. USER RANKING                  │");
		System.out.println("│               2. SONG RANKING                  │");
		System.out.println("│               0. BACK                          │");
		System.out.println("└────────────────────────────────────────────────┘");
		System.out.print("▶ 선택 : ");
	}
	
	public static void showUserRanking(ArrayList<User_rankingVo> list) {
		System.out.println("------------------------------------------------");
	    System.out.println("               🏁 USER RANKING 🏁");
	    System.out.println("------------------------------------------------");
	    System.out.println("RANK | PLAYER        | CORRECT | POINT");
	    System.out.println("------------------------------------------------");
		                 for (User_rankingVo vo : list) {

		                     System.out.printf("%-4d | %-13s | %-7d | %-5d%n",
		                             vo.getRanking(),
		                             vo.getUserId(),
		                             vo.getcorrectNumber(),
		                             vo.getPoint()
		                             );
		                 }
		System.out.println("--------------------------------------------------");
		System.out.println(" 1. 메인메뉴로 이동 / 0. 뒤로가기");
		System.out.println("--------------------------------------------------");
		System.out.print("▶ 선택 : ");
	}
	public static void showSongRanking(ArrayList<Song_imformationVo> list) {
		System.out.println("------------------------------------------------");
	    System.out.println("               🏁 SONG RANKING 🏁");
	    System.out.println("------------------------------------------------");
	    System.out.println("RANK | SONG        | CORRECT ");
	    System.out.println("------------------------------------------------");
		                 for (Song_imformationVo vo : list) {

		                     System.out.printf("%-4d | %-13s | %-7d\n",
		                             vo.getsongRanking(),
		                             vo.getsongName(),
		                             vo.getcorrectedNumber()
		                             );
		                 }
		System.out.println("--------------------------------------------------");
		System.out.println(" 1. 메인메뉴로 이동 / 0. 뒤로가기");
		System.out.println("--------------------------------------------------");
		System.out.print("▶ 선택 : ");
	}
	
}
