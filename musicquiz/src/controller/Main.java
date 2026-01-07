package controller;

import java.util.ArrayList;

import model.User_rankingDAO;
import model.User_rankingVo;

public class Main {

	public static void main(String[] args) {
		User_rankingDAO dao1 = new User_rankingDAO();
		ArrayList<User_rankingVo> result = dao1.selectAll();
		System.out.println(result);
	}

	

}
