package controller;

import jplayer.JMP3Player;

public class sound {
	
	private static JMP3Player player = new JMP3Player();
	
	static String path = "C:\\Users\\smhrd\\Desktop\\sorce\\sound\\";
	
	public static void correct() {
        player.stop();
        player.play(path+"/correct.mp3");
    }

    public static void wrong() {
        player.stop();
        player.play(path+"/wrong.mp3");
    }

    public static void next() {
        player.stop();
        player.play(path+"/next.mp3");
    }
    
    public static void clear() {
        player.stop();
        player.play(path+"/clear.mp3");
    }

    public static void stop() {
        player.stop();
    }

}
