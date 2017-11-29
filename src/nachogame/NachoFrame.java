package nachogame;

import javax.swing.JFrame;

public class NachoFrame extends JFrame {
	private Player player;
	public NachoFrame() {
		player = new Player();
		
	}
	
	public Player getPlayer() {
		return player;
	}

}
