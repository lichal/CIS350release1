package nachogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
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
	
	private JPanel buttonPanel;
	
	private JButton startButton;
	
	private JButton level1;
	
	private JButton level2;
	
	private JButton level3;
	
	private JButton level4;
	
	private JButton level5;
	
	private JButton level6;
	
	private JButton level7;
	
	private JButton level8;
	
	public NachoFrame() {
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		//buttonPanel.setLayout(new GridLayout(9, 7));
		
		startButton = new JButton("Start Game");
		startButton.setFont(Scaler.font);
		startButton.addActionListener(this);
		
		level1 = new JButton("LEVEL 1");
		level1.setFont(Scaler.font);
		level1.addActionListener(this);
		
		level2 = new JButton("LEVEL 2");
		level2.setFont(Scaler.font);
		level2.addActionListener(this);

		level3 = new JButton("LEVEL 3");
		level3.setFont(Scaler.font);
		level3.addActionListener(this);
		
		level4 = new JButton("LEVEL 4");
		level4.setFont(Scaler.font);
		level4.addActionListener(this);
		
		level5 = new JButton("LEVEL 5");
		level5.setFont(Scaler.font);
		level5.addActionListener(this);
		
		level6 = new JButton("LEVEL 6");
		level6.setFont(Scaler.font);
		level6.addActionListener(this);
		
		level7 = new JButton("LEVEL 7");
		level7.setFont(Scaler.font);
		level7.addActionListener(this);
		
		level8 = new JButton("LEVEL 8");
		level8.setFont(Scaler.font);
		level8.addActionListener(this);
		
		player = new Player();
		
		
		//setLayout(new GridLayout(9, 1));
//		add(startButton);
		add(level1);
		add(level2);
		add(level3);
		add(level4);
		add(level5);
		add(level6);
		add(level7);
		add(level8);
		
		mainFrame = new JFrame("SPACE ROCK NACHOS");
		mainFrame.setSize(Scaler.width, Scaler.height);
		mainFrame.add(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void newGame(LevelNum num) {
		// if player's xp is less then 0
		if (player.getXP() <= 0) {
			//start the new game with initial xp of 10
			player.setXP(10);
		}
		nachoGame = new NachoPanel(mainFrame, player, this, num);
		mainFrame.add(nachoGame);
	}
	
	public void paintComponent(final Graphics g) {
		g.drawImage(new BackGround(2).getBack(), 0, 0, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL1);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level1) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL1);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level2) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL2);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level3) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL3);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level4) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL4);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level5) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL5);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level6) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL6);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level7) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL7);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
		
		if (e.getSource() == level8) {
			
			// make a new nacho game
			newGame(LevelNum.LEVEL8);
			
			// set main panel visible to false when the game start
			setVisible(false);
		}
	}

	public static void main(final String[] args) {
		new NachoFrame();
	}

	

}
