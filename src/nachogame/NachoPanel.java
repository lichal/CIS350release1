package nachogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
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


	/** Used as a counter to delay shooting the cheese. */
	private int fire;

	/** A boolean that is determined by right arrow key presses. */
	private boolean driveRight;

	/** A boolean that is determined by left arrow key presses. */
	private boolean driveLeft;

	/** A boolean that is determined by space bar presses. */
	private boolean shoot;


	/** An int used to control the fire rate of the ship. */
	private int fireRate;

	/** a 60 second wave before the taco salad boss. */
	static final int NACHOENEMY = 6000;


	/** Determine if the player is playing the game. */
	private boolean gameOver;
	
	/** Determines if level complete */
	private boolean lComplete;

	/** The level the player is on. */
	private Level level;

	/** Game Score. */
	private int score;

	/** The player object for the game session. */
	private Player player;

	/** The pause dialog panel. */
	private MyDialog gamePausedDialog;

	/** The game over dialog panel. */
	private MyDialog gameOverDialog;
	
	/** The level complete dialog. */
	private MyDialog levelCompleteDialog;

	/** Color for the statistic Panel. */
	private Color statColor;


	/** The NachoFrame that "this" belongs to. */
	private NachoFrame mainPanel;
	
	/** The LevelNum of the current level being displayed. */
	private LevelNum num;

	/*******************************************************************
	 * The game panel for our nacho game.
	 * @param parent - the JFrame the nacho panel is displayed in.
	 * @param player - the player for the nacho panel and nacho game.
	 * @param mainPanel - the NachoFrame the nacho panel belongs to.
	 * @param num - the levelNum that corresponds to the level
	 *  being displayed.
	 ******************************************************************/
	public NachoPanel(final JFrame parent, final Player player, 
			final NachoFrame mainPanel, final LevelNum num) {
		/* instantiating all key driven booleans as false */

		level = new Level(LevelNum.LEVEL1);

		this.num = num;
		level = new Level(this.num);

		this.player = player;
		driveRight = false;
		driveLeft = false;
		shoot = false;
		gameOver = false;
		

		this.mainPanel = mainPanel;
		gamePausedDialog = new MyDialog(parent, "Game Paused", 
			"Main Menu", "Resume");
		gameOverDialog = new MyDialog(parent, "Game Over", 
			"Main Menu", "Restart");
		levelCompleteDialog = new MyDialog(parent, 
				"You beat this level!", "Main Menu", "Continue");


		/* instantiating a new game logic */
		game = new Logic();

		/* starting the timer that fires the ActionListener */
		tmr = new Timer(10, this);
		tmr.start();

		/* instantiating fire, count, timeElapsed, and startTime at 0 */
		fire = 0;

//		startTime = 0;
		score = 0;

		/* instantiate the statistic panel with color reddish 
		 * and alpha 0.5 */
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
				g.fillOval(sp.getX(), sp.getY(), sp.getWidth(),
					sp.getHeight());
			} else {
				g.drawImage(sp.getImage(), sp.getX(), 
					sp.getY(), this);
				if (sp instanceof Ship) {
					sp.setY(getHeight() - sp.getHeight());
				}
			}
		}

		/* displaying when ship is ready to fire again */
		if (fire >= fireRate) {
			g.setColor(Color.GREEN);
			g.fillRect(10, Scaler.HEIGHT - 10 - fireRate 
				* 4, 10, fireRate * 4);

			/* displays when ship is not ready to fire */
		} else {
			if (fire <= fireRate / 2) {
				g.setColor(Color.RED);
			}
			g.fillRect(10, Scaler.HEIGHT - 10 - fire
				* 4, 10, fire * 4);
		}
		g.drawRect(10, Scaler.HEIGHT - 10 - fireRate * 4,
			10, fireRate * 4);

		/*  */
		g.setColor(statColor);
		g.fillRect(Scaler.WIDTH / 6 * 5, 0, Scaler.WIDTH 
			/ 6, Scaler.HEIGHT / 6);

		/*  */
		g.setColor(Color.GRAY);
		int height1 = (int) (Scaler.HEIGHT * 2 / 100);
		int height3 = (int) (Scaler.HEIGHT * 6 / 100);
		int height2 = (int) (Scaler.HEIGHT * 10 / 100);
		g.drawString("Level Ranking:", Scaler.WIDTH / 6 * 5, height1);
		g.drawString(player.rankToString(player.getRank()),
			Scaler.WIDTH / 6 * 5, height3);
		g.drawString("XP " + player.getXP(), Scaler.WIDTH 
			/ 6 * 5, height2);
		
	}

	/******************************************************************
	 * This method returns user to the main menu.
	 *****************************************************************/
	private void goToMain() {
		setVisible(false);
		mainPanel.setVisible(true);
	}

	/******************************************************************
	 * This is the actionPerformed method that fires every time the Timer 
	 * tmr fires(every 10 milliseconds).
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
		
		if (lComplete) {
			tmr.stop();
			levelCompleteDialog.setVisible(true);
			if (levelCompleteDialog.getRestart()) {
				lComplete = false;
				levelCompleteDialog.setRestart(false);
				setVisible(false);
				mainPanel.newGame(num);
			}
			if (levelCompleteDialog.getBackToMain()) {
				levelCompleteDialog.setBackToMain(false);
				goToMain();
			}
		}
			
		fireRate = player.getFireRate();
		// loop a sample game
		// testRun();
		runGame(level);
		
		/* checking which keys are still pressed and calling 
		 * corresponding action */
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

		/* detecting collisions to be removed next time 
		 * actionPerformed is called. */
		game.detectCollisions();
		//
		if (player.getXP() <= 0) {
			gameOver = true;
		}
		/* incrementing fire and count to control fire rate
		 *  and enemy spawning. */
		fire++;

		
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
	 * Setting key Events for the GUI If the player presses the left 
	 * arrow the ship will drive left If the player pushes the right 
	 * arrow the ship will drive right If the player pushes the spacebar
	 * the ship will shoot cheese.
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
	 * Stops ship from traveling when the arrow keys are released 
	 * and stops the ship from shooting if the spacebar is released.
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
	 * This method clears expired projectiles that have either hit an
	 * enemy or left the screen from the game.
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
				if (c.getShot()) {
					player.incrementXP();
				} else {
					player.decrementXP();
				}
				game.getEnemies().remove(c);
			}
		}
		for (Enemy a : e) {
			game.getDeadEnemies().remove(a);
			game.removeSpaceCadet(a);
		}
	}


	



	/******************************************************************
	 * This method checks to see which keys are still pressed. If 
	 * the keys are still pressed they continue their action, 
	 * regardless of what other keys are pressed or released.
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
	 * @param level - The level being played in this NachoPanel.
	 *****************************************************************/
	private void runGame(final Level level) {
		level.run();
		level.incrementTime();
		if (level.getSpawnEnemy()) {
			game.spawnEnemy();
		}

		level.manageSpawn();
		if (level.getTime() >= level.getLevelTime()) {
			lComplete = true;
		}
	}

}
