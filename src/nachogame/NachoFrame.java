package nachogame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NachoFrame extends JPanel implements ActionListener {
	private Player player;
	
	private JFrame mainFrame;
	
	private NachoPanel nachoGame;
	
	private JButton startButton;
	
	public NachoFrame() {
		
		startButton = new JButton("Start Game");
		startButton.setFont(Scaler.font);
		startButton.addActionListener(this);
		
		player = new Player();
		
		add(startButton);
		
		mainFrame = new JFrame("SPACE ROCK NACHOS");
		mainFrame.setSize(Scaler.width, Scaler.height);
		mainFrame.add(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void newGame() {
		// if player's xp is less then 0
		if (player.getXP() <= 0) {
			//start the new game with initial xp of 10
			player.setXP(10);
		}
		nachoGame = new NachoPanel(mainFrame, player, this);
		mainFrame.add(nachoGame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			
			// make a new nacho game
			newGame();
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
	}

	public static void main(final String[] args) {
		new NachoFrame();
	}

	

}
