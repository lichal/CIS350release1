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
		
		startButton = new JButton("startGame");
		startButton.addActionListener(this);
		
		player = new Player();
		
		add(startButton);
		mainFrame = new JFrame("SPACE ROCK NACHO");
		mainFrame.setSize(Scaler.width, Scaler.height);
		mainFrame.add(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			nachoGame = new NachoPanel(mainFrame, player);
			mainFrame.add(nachoGame);
			startButton.setVisible(false);
		}
	}

	public static void main(final String[] args) {
		new NachoFrame();
	}

	

}
