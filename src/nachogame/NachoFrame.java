package nachogame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NachoFrame extends JFrame {
	private Player player;
	JPanel view;
	public NachoFrame() {
		player = new Player();
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setView(JPanel j) {
		view = j;
		setContentPane(j);
	}

}
