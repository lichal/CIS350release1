package nachogame;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.LineBorder;
/**********************************************************************
 * The main nacho frame, has the main menu, access to each levels.
 * 
 * @author Cheng Li
 *
 *********************************************************************/
public class NachoFrame extends JPanel implements ActionListener {
	
	/** Player of the current game. */
	private Player player;
	
	/** The main nacho JFrame. */
	private JFrame mainFrame;
	
	/** The nachoGame panel which has a game of nacho game. */
	private NachoPanel nachoGame;
	
	/** JPanel holds the buttons. */
	private JPanel buttonPanel;

	/** Level buttons 1 to 4 using MyMenuButton. */
	private MyMenuButton level1, level2, level3, level4;
	
	/** Level buttons 5 to 8 using MyMenuButton. */
	private MyMenuButton level5, level6, level7, level8;
	
	/** The JLabel contains Game Title. */
	private JLabel title;
	
	/** JPanel use to store title. */
	private JPanel titlePanel;
	
	/** This panel will combine both button and title panel. */
	private JPanel combinePanel;
	
	/** This panel is created as a place holder. */
	private JPanel holderPanel1;
	
	/** This panel is also created as a place holder. */
	private JPanel holderPanel2;
	
	/******************************************************************
	 * Main constructor of Nacho frame.
	 *****************************************************************/
	public NachoFrame() {
		// Declare an empty color has an alpha of 0.
		Color emptyColor = new Color(0, 0, 0, 0f);
		// This color is for button panel.
		Color normalColor = new Color(1, 0, 0, 0.3f);
		// Instantiate the title label and format it.
		title = new JLabel("SPACE ROCK NACHOS!");
		title.setFont(new Font("Serif", Font.PLAIN, 50));
		title.setForeground(Color.WHITE);
		
		// Instantiate all JPanels.
		buttonPanel = new JPanel();
		titlePanel = new JPanel();
		combinePanel = new JPanel();
		holderPanel1 = new JPanel();
		holderPanel2 = new JPanel();
		
		// Set the Gridlayout of all panels.
		buttonPanel.setLayout(new GridLayout(8, 1));
		titlePanel.setLayout(new GridLayout(1, 1));
		combinePanel.setLayout(new GridLayout(2, 1));
		holderPanel1.setLayout(new GridLayout(1, 1));
		holderPanel2.setLayout(new GridLayout(1, 1));
		
		// Set background of each panel.
		titlePanel.setBackground(emptyColor);
		holderPanel1.setBackground(emptyColor);
		combinePanel.setBackground(emptyColor);
		holderPanel2.setBackground(emptyColor);
		buttonPanel.setBackground(normalColor);
		
		// 
		titlePanel.add(title);
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		// Instantiate level buttons.
		level1 = new MyMenuButton("LEVEL 1");
		level2 = new MyMenuButton("LEVEL 2");
		level3 = new MyMenuButton("LEVEL 3");
		level4 = new MyMenuButton("LEVEL 4");
		level5 = new MyMenuButton("LEVEL 5");
		level6 = new MyMenuButton("LEVEL 6");
		level7 = new MyMenuButton("LEVEL 7");
		level8 = new MyMenuButton("LEVEL 8");
		
		// Add listener to each button.
		level1.addActionListener(this);
		level2.addActionListener(this);
		level3.addActionListener(this);
		level4.addActionListener(this);
		level5.addActionListener(this);
		level6.addActionListener(this);
		level7.addActionListener(this);
		level8.addActionListener(this);
		
		// Instantiate player.
		player = new Player();
		
		// Set panel layout.
		setLayout(new GridLayout(1, 3));
		
		// Add level buttons to button panel
		buttonPanel.add(level1);
		buttonPanel.add(level2);
		buttonPanel.add(level3);
		buttonPanel.add(level4);
		buttonPanel.add(level5);
		buttonPanel.add(level6);
		buttonPanel.add(level7);
		buttonPanel.add(level8);
		
		// add title and button panels together
		combinePanel.add(titlePanel);
		combinePanel.add(buttonPanel);
		// add all panels to the main JPanel
		add(holderPanel1);
		add(combinePanel);
		add(holderPanel2);
		
		// Start a new JFrame
		mainFrame = new JFrame("SPACE ROCK NACHOS");
		mainFrame.setSize(Scaler.WIDTH, Scaler.HEIGHT);
		mainFrame.add(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	/*******************************************************************
	 * This method returns the player of the game.
	 * @return player - the player object in the game.
	 ******************************************************************/
	public Player getPlayer() {
		return player;
	}

	/*******************************************************************
	 * This method starts a new NachoPanel that plays the corresponding 
	 * level for num.
	 * @param num - the level to be initiated.
	 ******************************************************************/
	public void newGame(final LevelNum num) {
		// if player's xp is less then 0
		if (player.getXP() <= 0) {
			//start the new game with initial xp of 10
			player.setXP(10);
		}
		nachoGame = new NachoPanel(mainFrame, player, this, num);
		mainFrame.add(nachoGame);
	}
	
	/******************************************************************
	 * This method paints the background for the nacho frame.
	 * @param g - The graphics for the frame.
	 *****************************************************************/
	public void paintComponent(final Graphics g) {
		g.drawImage(new BackGround(2).getBack(), 0, 0, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
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

	/*******************************************************************
	 * The main method for executing our game.
	 * @param args - the commands passed to our executable.
	 ******************************************************************/
	public static void main(final String[] args) {
		new NachoFrame();
	}

	

}
