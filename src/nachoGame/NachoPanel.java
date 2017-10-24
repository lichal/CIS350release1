package nachoGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**********************************************************************
 * This is the panel where all the action happens for our awesome space nachos
 * game.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @version 10-13-2017
 *********************************************************************/
public class NachoPanel extends JPanel
		implements ActionListener, KeyListener {

	/** instantiating timer used for game */
	Timer tmr = new Timer(10, this);

	/** Declaring game Logic */
	Logic game;

	/** A counter used to control auto enemy spawn rate */
	int count;

	/** Used as a counter to delay shooting the cheese */
	int fire;

	/** A boolean that is determined by right arrow key presses */
	boolean driveRight;

	/** A boolean that is determined by left arrow key presses */
	boolean driveLeft;

	/** A boolean that is determined by spacebar presses */
	boolean shoot;

	/** This checks the game time elapsed in 0.01 seconds */
	int timeElapsed;

	/** A start time for counting one second */
	int startTime;

	/** A boolean determine if one second counting should start */
	boolean startCount;

	static final int fireRate = 50;

	/** a 60 second wave before the taco salad boos */
	static final int nachoEnemy = 6000;

	/** A random to randomize how enemies are appear */
	private Random rand;
	
	/** Determine if the player is playing the game */
	private boolean gameOver;
	
	/** Determine if user paused the game or not */
	private boolean gamePaused;

	
	/*******************************************************************
	 * The game panel
	 ******************************************************************/
	public NachoPanel() {

		/* instantiating all key driven booleans as false */
		driveRight = false;
		driveLeft = false;
		shoot = false;
		gameOver = false;
		gamePaused = false;
		startCount = false;

		/* instantiating a new random */
		rand = new Random();

		/* instantiating a new game logic */
		game = new Logic();

		/* starting the timer that fires the ActionListener */
		tmr.start();
		tmr.addActionListener(this);

		/* instantiating fire, count, timeElapsed, and startTime at 0 */
		fire = 0;
		count = 0;
		timeElapsed = 0;
		startTime = 0;

		/* adding keyListener and requesting focus */
		addKeyListener(this);
		setFocusable(true);
		
		JFrame j = new JFrame("Animation Test");
		j.addKeyListener(this);
		j.setSize(Scaler.width, Scaler.height);
		j.setExtendedState(JFrame.MAXIMIZED_BOTH);
		j.add(this);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

	/**********************************************************************
	 * This method draws the graphics for the game
	 * 
	 * @param g
	 *********************************************************************/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/* draw the background image */
		g.drawImage(game.getBackGround().getBack(), 0, 0, this);

		/* draw the table */
		for(Table t : game.getTables()) {
			g.drawImage(t.getImage(), t.getX(), t.getY(), this);
		}

		/* drawing ship */
		g.drawImage(game.getShip().getImage(), game.getShip().getX(),
				game.getShip().getMaxY(), this);

		/* drawing projectiles */
		g.setColor(Color.yellow);
		for (Projectile x : game.getProjectiles()) {
			g.fillOval(x.getX(), x.getY(), 15, 35);
		}
		/* drawing enemies */
		for (Enemy x : game.getEnemies()) {
			g.drawImage(x.getImage(), x.getX(), x.getY(), this);
		}

		/* displaying when ship is ready to fire again */
		if (fire >= fireRate) {
			g.setColor(Color.GREEN);
			g.fillRect(10, 770 - fireRate * 2, 10, fireRate * 2);
		}

		/* displays when ship is not ready to fire */
		else {
			if (fire <= fireRate / 2)
				g.setColor(Color.RED);
			g.fillRect(10, 770 - fire * 2, 10, fire * 2);
		}
		g.drawRect(10, 770 - fireRate * 2, 10, fireRate * 2);
		
		/* Stop the game if the game is over */
		if (gameOver) {
			g.drawString("Game Over!", this.getWidth() / 2, this.getHeight() / 2);
			tmr.stop();
		}
		
		/* Pause the the game if player press Escape key */
		if (gamePaused) {
			g.drawString("Game Paused!", this.getWidth() / 2, this.getHeight() / 2);
			tmr.stop();
		}
		
	}

	/******************************************************************
	 * This is the actionPerformed method that fires every time the Timer tmr
	 * fires (every 10 milliseconds)
	 * 
	 * @param ActionEvent
	 *            e - Every 10 milliseconds
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		// loop a sample game
		testRun();
		
		/* start counting for 1 second when true */
		if (startCount) {
			startTime++;
		} else if (!startCount) {
			startTime = 0;
		}

		/*
		 * checking which keys are still pressed and calling corresponding
		 * action
		 */
		checkKeys();

		/* Moving ships and enemies and projectiles */
		game.moveShip();
		if (count % 3 == 0)
			game.moveEnemies();
		game.moveProjectiles();

		/* clearing enemies and projectiles that collided */
		clearSpentProjectiles();
		clearDeadEnemies();

		/* checking ships position to ensure it stays within boundaries */
		game.checkShipPosition();

		/*
		 * detecting collisions to be removed next time actionPerformed is
		 * called
		 */
		game.detectCollisions();

		/*
		 * incrementing fire and count to control fire rate and enemy spawning
		 */
		fire++;
		count++;
		timeElapsed++;

		/* repainting graphics */
		repaint();
		revalidate();
	}

	/******************************************************************
	 * Blank method stub necessary for implementing KeyListener
	 *****************************************************************/
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/*****************************************************************
	 * Setting key Events for the GUI If the player presses the left arrow the
	 * ship will drive left If the player pushes the right arrow the ship will
	 * drive right If the player pushes the spacebar the ship will shoot cheese.
	 * 
	 * @param KeyEvent
	 *            e - The key currently being pressed
	 ****************************************************************/
	@Override
	public void keyPressed(KeyEvent e) {

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
		
		if (c == KeyEvent.VK_ESCAPE) {
			gamePaused = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gamePaused = false;
			tmr.restart();
		}
	}

	/*******************************************************************
	 * Stops ship from traveling when the arrow keys are released and stops the
	 * ship from shooting if the spacebar is released.
	 * 
	 * @param KeyEvent
	 *            e - the key that is released
	 ******************************************************************/
	@Override
	public void keyReleased(KeyEvent e) {
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
		for (Projectile p : game.getSpentProjectiles())
			game.getProjectiles().remove(p);
	}

	/******************************************************************
	 * This method clears dead enemies (enemies with 0 health) from the game
	 * ArrayList of enemies.
	 *****************************************************************/
	private void clearDeadEnemies() {
		for (Enemy c : game.getDeadEnemies()) {
			c.setCounting(c.getCounting()+1);
//			startCount = true;
//			blinkEffect(c);
			
			
			if (c.getCounting() == 100) {
//				startCount = false;
//				startTime = 0;
				game.getEnemies().remove(c);
			}
			
//			game.getEnemies().remove(c);
		}
	}

	/******************************************************************
	 * This method makes the explosion blink
	 *****************************************************************/
	private void blinkEffect(Enemy c) {
		if (c.getCounting() > 30 && c.getCounting() < 60)
			c.setImage(null);
		else if (c.getCounting() < 100)
			c.doLogic();
	}

	/******************************************************************
	 * This method checks to see which keys are still pressed. If the keys are
	 * still pressed they continue their action, regardless of what other keys
	 * are pressed or released.
	 *****************************************************************/
	private void checkKeys() {

		// checking left arrow key
		if (driveLeft)
			game.driveLeft();

		// checking right arrow key
		if (driveRight)
			game.driveRight();

		// checking spacebar
		if (shoot) {
			if (fire >= fireRate) {
				game.getProjectiles().add(new Projectile(
						game.getShip().getX() + 53, 670, 15, 15));
				fire = 0;
			}
		}
	}

	/*******************************************************************
	 * This method randomly generates enemy to appear on the screen.
	 * 5 cases, enemy might appear on 5 different places.
	 ******************************************************************/
	private void generateEnemy() {
		// generate random numbers
		int track = rand.nextInt(5);
		switch (track) {
		case 0:
			game.spawnEnemy(25);
			break;
		case 1:
			game.spawnEnemy(225);
			break;
		case 2:
			game.spawnEnemy(425);
			break;
		case 3:
			game.spawnEnemy(625);
			break;
		case 4:
			game.spawnEnemy(825);
			break;
		}
	}

	/*******************************************************************
	 * This method generate a wave of enemy on the screen, enemy will
	 * appear on the middle three tracks.
	 ******************************************************************/
	private void generateWave() {
		// generate random numbers
		int track = rand.nextInt(3);

		switch (track) {
		case 0:
			game.spawnEnemy(225);
			break;
		case 1:
			game.spawnEnemy(425);
			break;
		case 2:
			game.spawnEnemy(625);
			break;
		}
	}
	
	/*******************************************************************
	 * This method runs a basic game of the space rock nacho.
	 * 
	 ******************************************************************/
	private void testRun(){
		/* Auto spawning enemies for testing */
		/* First 30 seconds*/
		if (timeElapsed < nachoEnemy/2) {
			if (count % 300 == 0)
				generateEnemy();
		}
		/* 30 second to 48 second, wave 1 */
		else if (timeElapsed >= nachoEnemy / 2
				&& timeElapsed <= nachoEnemy * 0.8) {
			if (count % 150 == 0)
				generateWave();
		}
		else if (timeElapsed > nachoEnemy * 0.8 && timeElapsed < nachoEnemy) {
			if (count % 300 == 0)
				generateEnemy();
		}
		/* */
		else if (timeElapsed == nachoEnemy && timeElapsed <= nachoEnemy * 1.5) {
			generateBoss();
		}  else if (timeElapsed > nachoEnemy * 3.5){
			gameOver = true;
		}
	}

	/*******************************************************************
	 * Generate a boss object
	 ******************************************************************/
	private void generateBoss() {
		game.spawnBoss();
	}

	/******************************************************************
	 * Testing our source code
	 * 
	 * @param args
	 *****************************************************************/
	public static void main(String args[]) {
		new NachoPanel();
	}
}
