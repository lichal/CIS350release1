package nachogame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**********************************************************************
 * This is the panel where all the action happens for our awesome space nachos
 * game.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-13-2017
 *********************************************************************/
public class NachoPanel extends JPanel implements ActionListener, KeyListener {

	/** instantiating timer used for game. */
	private Timer tmr;

	/** Declaring game Logic. */
	private Logic game;

	/** A counter used to control auto enemy spawn rate. */
	private int count;

	/** Used as a counter to delay shooting the cheese. */
	private int fire;

	/** A boolean that is determined by right arrow key presses. */
	private boolean driveRight;

	/** A boolean that is determined by left arrow key presses. */
	private boolean driveLeft;

	/** A boolean that is determined by space bar presses. */
	private boolean shoot;

	/** This checks the game time elapsed in 0.01 seconds. */
	private int timeElapsed;

//	/** A start time for counting one second. */
//	private int startTime;
//
//	/** A boolean determine if one second counting should start. */
//	private boolean startCount;

	/** An int used to control the fire rate of the ship. */
	private int fireRate;

	/** a 60 second wave before the taco salad boss. */
	static final int NACHOENEMY = 6000;

	/** A random to randomize how enemies are appear. */
	private Random rand;

	/** Determine if the player is playing the game. */
	private boolean gameOver;

	/** The level the player is on */
	private Level level;

	/** Fast shooting effect on */
	private boolean fastShooting;

	/** Game Score */
	private int score;

	// private JFrame j;

	private Player player;

	private MyDialog gamePausedDialog;

	private MyDialog gameOverDialog;

	/** Color for the statistic Panel */
	private Color statColor;


	//private KeyListener key;

	private int levelRank;

	//private int actualHeight;

	//private int actualWidth;

//	private KeyListener key;
//
//	private int levelRank;
//
//	private int actualHeight;
//
//	private int actualWidth;


	private NachoFrame mainPanel;
	
	private LevelNum num;

	/*******************************************************************
	 * The game panel for our nacho game.
	 ******************************************************************/
	public NachoPanel(JFrame parent, Player player, NachoFrame mainPanel, LevelNum num) {
		/* instantiating all key driven booleans as false */

		level = new Level(LevelNum.LEVEL1);

		this.num = num;
		level = new Level(this.num);

		this.player = player;
		driveRight = false;
		driveLeft = false;
		shoot = false;
		gameOver = false;
		
		fastShooting = false;

		this.mainPanel = mainPanel;
		gamePausedDialog = new MyDialog(parent, "Game Paused", "Quit", "Resume");
		gameOverDialog = new MyDialog(parent, "Game Over", "Main Menu", "Restart");

		/* instantiating a new random */
		rand = new Random();

		/* instantiating a new game logic */
		game = new Logic();

		/* starting the timer that fires the ActionListener */
		tmr = new Timer(10, this);
		tmr.start();

		/* instantiating fire, count, timeElapsed, and startTime at 0 */
		fire = 0;
		count = 0;
		timeElapsed = 0;
//		startTime = 0;
		score = 0;

		/* instantiate the statistic panel with color reddish and alpha 0.5 */
		statColor = new Color(1, 0, 0, 0.5f);

		/* adding keyListener and requesting focus */
		addKeyListener(this);
		setFocusable(true);
		parent.setResizable(false);
	}

	/**********************************************************************
	 * This method draws the graphics for the game.
	 * 
	 * @param g
	 *            - the paint component.
	 *********************************************************************/
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.setFont(Scaler.font);

		//actualHeight = getHeight();
		//actualWidth = getWidth();




		/* draw the background image */
		g.drawImage(game.getBackGround().getBack(), 0, 0, this);

		/* drawing everything at once */
		g.setColor(Color.yellow);
		for (SpaceObject sp : game.getSpaceObjects()) {
			if (sp instanceof Projectile) {
				g.fillOval(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight());
			} else {
				g.drawImage(sp.getImage(), sp.getX(), sp.getY(), this);
				if (sp instanceof Ship) {
					sp.setY(getHeight() - sp.getHeight());
				}
			}
		}

		/* displaying when ship is ready to fire again */
		if (fire >= fireRate) {
			g.setColor(Color.GREEN);
			g.fillRect(10, Scaler.height - 10 - fireRate * 4, 10, fireRate * 4);

			/* displays when ship is not ready to fire */
		} else {
			if (fire <= fireRate / 2) {
				g.setColor(Color.RED);
			}
			g.fillRect(10, Scaler.height - 10 - fire * 4, 10, fire * 4);
		}
		g.drawRect(10, Scaler.height - 10 - fireRate * 4, 10, fireRate * 4);

		/*  */
		g.setColor(statColor);
		g.fillRect(Scaler.width / 6 * 5, 0, Scaler.width / 6, Scaler.height / 6);

		/*  */
		g.setColor(Color.GRAY);
		//g.drawString("Level Ranking: " + levelRank, Scaler.width / 6 * 5, 10);
		//g.drawString("XP " + player.getXP(), Scaler.width / 6 * 5, 50);
		int height1 = (int)(Scaler.height * 2 / 100);
		int height3 = (int)(Scaler.height * 6 / 100);
		int height2 = (int)(Scaler.height * 10 / 100);
		g.drawString("Level Ranking:", Scaler.width / 6 * 5, height1);
		g.drawString(player.rankToString(player.getRank()),Scaler.width / 6 * 5, height3 );
		g.drawString("XP " + player.getXP(), Scaler.width / 6 * 5, height2);
		
	}

	private void goToMain() {
		setVisible(false);
		mainPanel.setVisible(true);
	}

	/******************************************************************
	 * This is the actionPerformed method that fires every time the Timer tmr
	 * fires(every 10 milliseconds).
	 * 
	 * @param e
	 *            - Every 10 milliseconds
	 ******************************************************************/
	@Override
	public void actionPerformed(final ActionEvent e) {
		/* Stop the game if the game is over */
		if (gameOver) {
			tmr.stop();
			gameOverDialog.setVisible(true);
			if (gameOverDialog.getRestart()) {
				gameOver = false;
				gameOverDialog.setRestart(false);
				setVisible(false);
				mainPanel.newGame(num);
			}
			if (gameOverDialog.getBackToMain()) {
				gameOverDialog.setBackToMain(false);
				goToMain();
			}
		}
		fireRate = player.getFireRate();
		// loop a sample game
		// testRun();
		runGame(level);

		/* start counting for 1 second when true */
//		if (startCount) {
//			startTime++;
//		} else if (!startCount) {
//			startTime = 0;
//		}

		/*
		 * checking which keys are still pressed and calling corresponding
		 * action
		 */
		checkKeys();

		/* Moving EVERYTHING AT ONCE */
		for (SpaceObject sp : game.getSpaceObjects()) {
			sp.move();
		}

		/*
		 * checking ships position to ensure it stays within boundaries.
		 */
		game.checkShipPosition();
		game.checkProjectileStatus();
		
		/* clearing enemies and projectiles that collided */
		clearSpentProjectiles();
		clearDeadEnemies();

		/*
		 * detecting collisions to be removed next time actionPerformed is
		 * called.
		 */
		game.detectCollisions();
		//
		if (player.getXP() <= 0)
			gameOver = true;

		/*
		 * incrementing fire and count to control fire rate and enemy spawning.
		 */
		count++;
		fire++;
		timeElapsed++;
		
		/* Checks if special effect is on*/
		/* repainting graphics */
		repaint();
		revalidate();

		// System.out.println(timeElapsed);
	}

	/******************************************************************
	 * Blank method stub necessary for implementing KeyListener.
	 *****************************************************************/
	@Override
	public void keyTyped(final KeyEvent e) {

	}

	/*****************************************************************
	 * Setting key Events for the GUI If the player presses the left arrow the
	 * ship will drive left If the player pushes the right arrow the ship will
	 * drive right If the player pushes the spacebar the ship will shoot cheese.
	 * 
	 * @param e
	 *            The key currently being pressed
	 ****************************************************************/
	@Override
	public void keyPressed(final KeyEvent e) {

		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			// game.driveLeft();
			driveLeft = true;
		}

		if (c == KeyEvent.VK_RIGHT) {
			// game.driveRight();
			driveRight = true;
		}

		if (c == KeyEvent.VK_SPACE) {
			shoot = true;
		}

		/* Pause the the game if player press Escape key */
		if (c == KeyEvent.VK_ESCAPE) {
			tmr.stop();

			// set the game pause dialog to visible
			gamePausedDialog.setVisible(true);
			if (gamePausedDialog.getResumed()) {
				tmr.restart();
				gamePausedDialog.setResumed(false);
			} else if (gamePausedDialog.getBackToMain()) {
				gamePausedDialog.setBackToMain(false);
				goToMain();
			}
		}
	}

	/*******************************************************************
	 * Stops ship from traveling when the arrow keys are released and stops the
	 * ship from shooting if the spacebar is released.
	 * 
	 * @param e
	 *            - the key that is released
	 ******************************************************************/
	@Override
	public void keyReleased(final KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
			driveLeft = false;
		}

		if (c == KeyEvent.VK_RIGHT) {
			driveRight = false;
		}

		if (c == KeyEvent.VK_SPACE) {
			shoot = false;
		}

		game.stopShip();
	}

	/******************************************************************
	 * This method clears expired projectiles that have either hit an enemy or
	 * left the screen from the game.
	 *****************************************************************/
	private void clearSpentProjectiles() {
		for (Projectile p : game.getSpentProjectiles()) {
			game.getProjectiles().remove(p);
			game.removeSpaceCadet(p);
		}
	}

	/******************************************************************
	 * This method clears dead enemies (enemies with 0 health) from the game
	 * ArrayList of enemies.
	 *****************************************************************/
	private void clearDeadEnemies() {
		ArrayList<Enemy> e = new ArrayList<Enemy>();
		for (Enemy c : game.getDeadEnemies()) {
			game.getEnemies().remove(c);
			c.incrementCount();
			//if (c.getCounting() % 40 == 0) {
			if (c.getCounting() >= 40) {
				e.add(c);
//				startCount = false;
				c.resetCount();
				if (c.getShot())
					player.incrementXP();
				else
					player.decrementXP();
				game.getEnemies().remove(c);
			}
		}
		for (Enemy a : e) {
			game.getDeadEnemies().remove(a);
			game.removeSpaceCadet(a);
		}
	}


	



	/******************************************************************
	 * This method checks to see which keys are still pressed. If the keys are
	 * still pressed they continue their action, regardless of what other keys
	 * are pressed or released.
	 *****************************************************************/
	private void checkKeys() {

		// checking left arrow key
		if (driveLeft) {
			game.driveLeft();
		}

		// checking right arrow key
		if (driveRight) {
			game.driveRight();
		}

		// checking spacebar
		if (shoot) {
			if (fire >= fireRate) {
				Projectile p = new Projectile(game.getShip());
				game.getProjectiles().add(p);
				game.addSpaceCadet(p);
				fire = 0;
			}
		}
	}



	/*******************************************************************
	 * This method generate a wave of enemy on the screen, enemy will appear on
	 * the middle three tracks.
	 ******************************************************************/

	/*******************************************************************
	 * This method runs a basic game of the space rock nacho.
	 * 
	 ******************************************************************/
	private void testRun() {
		/* Auto spawning enemies for testing */
		/* First 30 seconds */

		if (timeElapsed < NACHOENEMY / 2) {
			if (count % 100 == 0) {
				game.spawnEnemy();
			}

			/* 30 second to 48 second, wave 1 */
		} else if (timeElapsed >= NACHOENEMY / 2 && timeElapsed <= NACHOENEMY * 0.8) {
			if (count % 150 == 0) {
				game.spawnEnemy();
			}
		} else if (timeElapsed > NACHOENEMY * 0.8 && timeElapsed < NACHOENEMY) {
			if (count % 300 == 0) {
				game.spawnEnemy();
			}
		} else if (timeElapsed >= NACHOENEMY && timeElapsed <= NACHOENEMY * 1.5) {
			generateBoss();
		} else if (timeElapsed >= NACHOENEMY * 1.5) {
			gameOver = true;
		}
	}

	/*******************************************************************
	 * Generate a boss into the game.
	 ******************************************************************/
	private void generateBoss() {
		game.spawnBoss();
	}


	/*******************************************************************
	 * This method calculates the game score and returns it.
	 * 
	 * @return score - current game score
	 ******************************************************************/
	private int calculateScore() {
		int destroy = game.getNumEnemyDestroyed();
		int miss = game.getEnemyMissed();
		score = destroy - miss;

		return score;
	}

	/******************************************************************
	 * Test Method for level system.
	 * 
	 * @param level
	 *****************************************************************/
	private void runGame(Level level) {
		level.run();
		level.incrementTime();
		if (level.getSpawnEnemy()) {
			game.spawnEnemy();
		}

		level.manageSpawn();
		if (level.getTime() >= level.getLevelTime()) {
			gameOver = true;
		}
	}

}
