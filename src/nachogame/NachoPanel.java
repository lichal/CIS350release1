package nachogame;

import java.awt.Color;
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
 * This is the panel where all the action happens for our awesome 
 * space nachos game.
 * 
 * @author Jon DeWent
 * @author Cheng Li
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

	/** A start time for counting one second. */
	private int startTime;

	/** A boolean determine if one second counting should start. */
	private boolean startCount;

	/** An int used to control the fire rate of the ship. */
	private int fireRate = 20;

	/** a 60 second wave before the taco salad boss. */
	static final int NACHOENEMY = 6000;

	/** A random to randomize how enemies are appear. */
	private Random rand;

	/** Determine if the player is playing the game. */
	private boolean gameOver;

	/** The level the player is on */
	private Level level;
	
	/** Slow motion effect on */
	private boolean slowMotion;
	
	/** Fast shooting effect on */
	private boolean fastShooting;
	
	/** Game Score */
	private int score;
	
	private JFrame j;
	
	private Player player;
	
	private GamePause gamePaused;
	
	/** Color for the statistic Panel */
	private Color statColor;
	
	private int levelRank;
	
	/*******************************************************************
	 * The game panel for our nacho game.
	 ******************************************************************/
	public NachoPanel(Player player) {
		/* instantiating all key driven booleans as false */
		level = new Level(LevelNum.LEVEL8);
		this.player = player;
		driveRight = false;
		driveLeft = false;
		shoot = false;
		gameOver = false;
		startCount = false;
		slowMotion = false;
		fastShooting = false;

		gamePaused = new GamePause(j);
		
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
		startTime = 0;
		score = 0;
		
		/* instantiate the statistic panel with color reddish and alpha 0.5*/
		statColor = new Color(1, 0, 0, 0.5f);

		/* adding keyListener and requesting focus */
		addKeyListener(this);
		setFocusable(true);
//
//		j = new JFrame("SPACE ROCK NACHO");
//		j.addKeyListener(this);
//		j.setSize(Scaler.width, Scaler.height+22);
//		j.setExtendedState(JFrame.MAXIMIZED_BOTH);	// This would set the screen size to max always, conflicted with setSize()
//		j.setUndecorated(true); // This would set the game panel to full screen
//		j.add(this);
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setVisible(true);
	}

	/**********************************************************************
	 * This method draws the graphics for the game.
	 * @param g - the paint component.
	 *********************************************************************/
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		/* draw the background image */
		g.drawImage(game.getBackGround().getBack(), 0, 0, this);

		/* draw the table */
//		for (Table t : game.getTables()) {
//			g.drawImage(t.getImage(), t.getX(), t.getY(), this);
//		}

		/* drawing ship */
		g.drawImage(game.getShip().getImage(), game.getShip().getX(),
				game.getShip().getMaxY(), this);

		/* drawing projectiles */
		g.setColor(Color.yellow);
		for (Projectile x : game.getProjectiles()) {
			g.fillOval(x.getX(), x.getY(), x.getWidth(), x.getHeight());
		}
		/* drawing enemies */
		for (Enemy x : game.getEnemies()) {
			g.drawImage(x.getImage(), x.getX(), x.getY(), this);
		}
		
		for (Enemy x : game.getDeadEnemies()) {
			g.drawImage(x.getImage(), x.getX(), x.getY(), this);
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

		/* Stop the game if the game is over */
		if (gameOver) {
			g.drawString("Game Over!", this.getWidth() / 2,
					this.getHeight() / 2);
			tmr.stop();
		}
		
		/*  */
		g.setColor(statColor);
		g.fillRect(Scaler.width/6 * 5, 0, Scaler.width/6, Scaler.height/6);
	
		/*  */
		g.setColor(Color.GRAY);
		g.drawString("Level Ranking: " + levelRank, Scaler.width/6 * 5, 10);
		g.drawString("XP " + player.getXP(), Scaler.width/6 * 5, 50);
	}

	/******************************************************************
	 * This is the actionPerformed method that fires every time the 
	 * Timer tmr fires(every 10 milliseconds).
	 * 
	 * @param e - Every 10 milliseconds
	 ******************************************************************/
	@Override
	public void actionPerformed(final ActionEvent e) {
		fireRate = player.getFireRate();
		// loop a sample game
//		testRun();
		runGame(level);

		/* start counting for 1 second when true */
		if (startCount) {
			startTime++;
		} else if (!startCount) {
			startTime = 0;
		}

		/*
		 * checking which keys are still pressed and calling 
		 * corresponding action
		 */
		checkKeys();

		/* Moving ships and enemies and projectiles */
		game.moveShip();
		game.moveEnemies();

		game.moveProjectiles();

		/* clearing enemies and projectiles that collided */
		clearSpentProjectiles();
		clearDeadEnemies();

		/* checking ships position to ensure it stays 
		 * within boundaries. */
		game.checkShipPosition();

		/*
		 * detecting collisions to be removed next time 
		 * actionPerformed is called.
		 */
		game.detectCollisions();
//		
		if (player.getXP() <= 0)
			gameOver = true;

		/*
		 * incrementing fire and count to control fire rate 
		 * and enemy spawning.
		 */
		count++;
		fire++;
		timeElapsed++;
		
		/*
		 * Checks if special effect is on
		 */
		fastShooting();
		slowMotion();
		
		/* repainting graphics */
		repaint();
		revalidate();
		
//		System.out.println(timeElapsed);
	}

	/******************************************************************
	 * Blank method stub necessary for implementing KeyListener.
	 *****************************************************************/
	@Override
	public void keyTyped(final KeyEvent e) {

	}

	/*****************************************************************
	 * Setting key Events for the GUI If the player presses the 
	 * left arrow the ship will drive left If the player pushes the 
	 * right arrow the ship will drive right If the player pushes 
	 * the spacebar the ship will shoot cheese.
	 * 
	 * @param e  The key currently being pressed
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
			gamePaused.setVisible(true);
			if(gamePaused.getResumed()){
				tmr.restart();
			}
		}
	}

	/*******************************************************************
	 * Stops ship from traveling when the arrow keys are released and
	 *  stops the ship from shooting if the spacebar is released.
	 * 
	 * @param e - the key that is released
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
	 * This method clears expired projectiles that 
	 * have either hit an enemy or left the screen from the game.
	 *****************************************************************/
	private void clearSpentProjectiles() {
		for (Projectile p : game.getSpentProjectiles()) {
			game.getProjectiles().remove(p);
		}
	}
	
	/******************************************************************
	 * This method clears dead enemies (enemies with 0 health) from the game
	 * ArrayList of enemies.
	 *****************************************************************/
	private void clearDeadEnemies() {
		ArrayList <Enemy> e = new ArrayList<Enemy>();
		for (Enemy c : game.getDeadEnemies()) {
			game.getEnemies().remove(c);
				c.incrementCount();
			if(c.getCounting() % 40 == 0) {
				e.add(c);
				startCount = false;
				c.resetCount();
				if (c.getShot())
					player.incrementXP();
				else
					player.decrementXP();
				game.getEnemies().remove(c);
				System.out.println(game.getEnemies().size());	
			}
		}
		for (Enemy a : e) {
		game.getDeadEnemies().remove(a);
		}
	}

	/******************************************************************
	 * This method makes the explosion blink.
	 * @param c - The enemy that is exploding.
	 *****************************************************************/
//	private void blinkEffect(final Enemy c) {
//		if (c.getCounting() > 30 && c.getCounting() < 60) {
//			c.setImage(null);
//		} else if (c.getCounting() < 100) {
//			c.doLogic();
//		}
//	}
	//FIXME: Not working right now!
	/******************************************************************
	 * This method checks to see which keys are still pressed.
	 * If the keys are still pressed they continue their action, 
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
				game.getProjectiles().add(new Projectile(game.getShip()));
				fire = 0;
			}
		}
	}

	/*******************************************************************
	 * This method randomly generates enemy to appear on the screen.
	 * 5 cases, enemy might appear on 5 different places.
	 ******************************************************************/
	private void generateEnemy() {
//		// generate random numbers
//		int track = rand.nextInt(5);
//		
//		switch (track) {
//		case 0:
//			game.spawnEnemy(25);
//			break;
//		case 1:
//			game.spawnEnemy(Scaler.width * 2/5);
//			break;
//		case 2:
//			game.spawnEnemy(Scaler.width * 3/5);
//			break;
//		case 3:
//			game.spawnEnemy(Scaler.width * 4/5);
//			break;
//		case 4:
//			game.spawnEnemy(Scaler.width * 5/5);
//			break;
//		default:
//			break;
//		}
	}

	/*******************************************************************
	 * This method generate a wave of enemy on the screen, enemy will 
	 * appear on the middle three tracks.
	 ******************************************************************/
	private void generateWave() {
		// generate random numbers
//		int track = rand.nextInt(3);
//
//		switch (track) {
//		case 0:
//			game.spawnEnemy(225);
//			break;
//		case 1:
//			game.spawnEnemy(425);
//			break;
//		case 2:
//			game.spawnEnemy(625);
//			break;
//		default:
//			break;
//
//		}
	}

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
//				generateEnemy();
			}
		
		/* 30 second to 48 second, wave 1 */
		} else if (timeElapsed >= NACHOENEMY / 2 
				&& timeElapsed <= NACHOENEMY * 0.8) {
			if (count % 150 == 0) {
				game.spawnEnemy();
//				generateWave();
			}
		} else if (timeElapsed > NACHOENEMY * 0.8 
				&& timeElapsed < NACHOENEMY) {
			if (count % 300 == 0) {
				game.spawnEnemy();
//				generateEnemy();
			}
		} else if (timeElapsed >= NACHOENEMY && timeElapsed 
				<= NACHOENEMY * 1.5) {
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
	 * Special effect, fast shooting
	 ******************************************************************/
	private void fastShooting(){
		while (fastShooting) {
			fireRate = fireRate/2;
		}
	}
	
	/*******************************************************************
	 * Special effect, slow motion on enemy
	 ******************************************************************/
	private void slowMotion() {
		while (slowMotion) {
			game.getShip().setVelX(game.getShip().getVelX() * 2);

			for (Projectile p : game.getProjectiles()) {
				p.setVelY(p.getVelY() * 2);
			}

			for (Enemy e : game.getEnemies()) {
				e.setVelY(e.getVelY() / 2);
			}
		}
	}
	
	/*******************************************************************
	 * This method calculates the game score and returns it.
	 * 
	 * @return score - current game score
	 ******************************************************************/
	private int calculateScore(){
		int destroy = game.getNumEnemyDestroyed();
		int miss = game.getEnemyMissed();
		score =  destroy - miss;
		
		return score;
	}
	
	/******************************************************************
	 * Test Method for level system.
	 * @param level
	 *****************************************************************/
	private void runGame(Level level) {
		level.run();
		level.incrementTime();
		if (level.getSpawnEnemy()) {
			game.spawnEnemy();
		}
//		if (level.getSpawnBoss()) {
//			game.spawnBoss();
//		}
		level.manageSpawn();
		if (level.getTime() >= level.getLevelTime()) {
			gameOver = true;
		}
	
	}
	
	/******************************************************************
	 * Testing our source code.
	 * @param args - the arguments to be passed.
	 *****************************************************************/
	public static void main(final String[] args) {
		NachoFrame n = new NachoFrame();
		n.setVisible(true);
		NachoPanel p = new NachoPanel(n.getPlayer());
		n.setView(p);
		n.setVisible(true);
		n.pack();
		
		
//		j = new JFrame("SPACE ROCK NACHO");
		n.addKeyListener(p);
		n.setSize(Scaler.width, Scaler.height+22);
		
		n.add(p);
		n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		n.setVisible(true);
	}
}
