package controller;

import java.util.ArrayList;

import model.Song_imformationDAO;
import model.Song_imformationVo;
import model.User_imformationDAO;
import model.User_imformationVo;
import model.User_rankingDAO;
import model.User_rankingVo;

public class Main {

	public static void main(String[] args) {
		User_rankingDAO urdao = new User_rankingDAO();
		ArrayList<User_rankingVo> result = urdao.selectAll();
		System.out.println(result);
		
		Song_imformationDAO sidao = new Song_imformationDAO();
		ArrayList<Song_imformationVo> result2 = sidao.selectAll();
		System.out.println(result2);
		
		User_imformationDAO uidao = new User_imformationDAO();
		ArrayList<User_imformationVo> result3 = uidao.selectAll();
		System.out.println(result3);
		
	}

	

}
