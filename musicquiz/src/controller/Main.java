package controller;

import java.util.ArrayList;

import model.Song_imformationDAO;
import model.User_rankingDAO;
import model.User_rankingVo;

public class Main {

	public static void main(String[] args) {
		User_rankingDAO urdao = new User_rankingDAO();
		ArrayList<User_rankingVo> result = urdao.selectAll();
		System.out.println(result);
		
		Song_imformationDAO sidao = new Song_imformationDAO();
		
	}

	

}
