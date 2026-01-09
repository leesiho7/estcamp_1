package controller;

import jplayer.JMP3Player;

public class sound {
	
	private static JMP3Player player = new JMP3Player();
	
	static String path = "C:\\Users\\smhrd\\Desktop\\sorce\\sound\\";
	
	public static void correct() {
        player.stop();
        player.play(path+"/true.mp3");
    }

    public static void wrong() {
        player.stop();
        player.play(path+"/fail.mp3");
    }

    public static void next() {
        player.stop();
        player.play(path+"/endbackground.mp3");
    }
    
    public static void clear() {
        player.stop();
        player.play(path+"/startbackgraound.mp3");
    }

    public static void stop() {
        player.stop();
    }

}
