package nachogame;

import java.util.ArrayList;

import javax.swing.JPanel;

/**********************************************************************
 * This is the game engine for our space nachos game.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 *********************************************************************/
public class Logic extends JPanel {
	/** The ship in the game. */
	private Ship ship;
	
	/** The ArrayList of enemies in the game. */
	private ArrayList<Enemy> enemies;
	
	/** The ArrayList of Projectiles in the game. */
	private ArrayList<Projectile> projectiles;
	
	/** The ArrayList of spent projectiles in the game. */
	private ArrayList<Projectile> spentProjectiles;
	
	/** The arrayList of dead enemies in the game. */
	private ArrayList<Enemy> deadEnemies;
	
	/** The background for the game. */
	private BackGround background;
	
	/** Number of enemy destroyed. */
	private int numEnemyDestroyed;
	
	/** Number of enemy missed. */
	private int enemyMissed;
	
	/** An arraylist of the various entities in the game. */
	private ArrayList<SpaceObject> spaceObjects;
	

	/******************************************************************
	 * The constructor for Logic.
	 *****************************************************************/
	public Logic() {
		
		/** initialize the number of enemy destroy and miss */
		numEnemyDestroyed = 0;
		enemyMissed = 0;

		background = new BackGround(1);

		ship = new Ship();
		enemies = new ArrayList<Enemy>();

		// boss = new TacoBoss("Art/tacosalad.png", 225, -200, 25);
		projectiles = new ArrayList<Projectile>();

		spentProjectiles = new ArrayList<Projectile>();
		spaceObjects = new ArrayList<SpaceObject>();

		deadEnemies = new ArrayList<Enemy>();
		
		// add stuff to spaceObjects to make sure it all gets added in
		addSpaceCadet(ship);
	}

	/******************************************************************
	 * This method gets the background for the game.
	 * 
	 * @return background - the game background.
	 *****************************************************************/
	public BackGround getBackGround() {
		return background;
	}
	
	
	/******************************************************************
	 * This method puts all SpaceObjects in a list - this way,
	 * every object that has to move on screen can just be called
	 * from this list in a loop (after they are added to it).
	 * @param s - the spaceObject to be added to the game.
	 *****************************************************************/
	public void addSpaceCadet(final SpaceObject s) {
		if (!spaceObjects.contains(s)) {
			spaceObjects.add(s);
		}
	}
	
	
	/******************************************************************
	 * This method removes a SpaceObject from the list. They have been
	 * uninvited to the party.
	 * @param s - the spaceObject to be removed.
	 *****************************************************************/
	public void removeSpaceCadet(final SpaceObject s) {
		if (spaceObjects.contains(s)) {
			spaceObjects.remove(s);
		}
	}
	
	
	
	
	/*****************************************************************
	 * This method returns the number of enemy destroyed by player.
	 * 
	 * @return numEnemyDestroyed - the number of enemy destroyed
	 *****************************************************************/
	public int getNumEnemyDestroyed() {
		return numEnemyDestroyed;
	}
	
	/*****************************************************************
	 * This method returns the number of enemy player missed.
	 * 
	 * @return numEnemyDestroyed - the number of enemy missed
	 *****************************************************************/
	public int getEnemyMissed() {
		return enemyMissed;
	}

	/*****************************************************************
	 * This method moves the projectiles vertically as well as return the
	 * projectiles that are to be removed from the projectiles ArrayList.
	 ****************************************************************/
	public void checkProjectileStatus() {
		for (Projectile x : projectiles) {
			if (x.getY() <= -x.getHeight()) {
				spentProjectiles.add(x);
			}
		}
	}

	/*****************************************************************
	 * This method sets ships x velocity to 0.
	 *****************************************************************/
	public void stopShip() {
		ship.setVelX(Ship.getV0());
	}

	/*****************************************************************
	 * This method increments x velocity to drive right.
	 *****************************************************************/
	public void driveRight() {
		ship.setVelX(Ship.getvR());
	}

	/*****************************************************************
	 * This method decrements x velocity to drive left.
	 *****************************************************************/
	public void driveLeft() {
		ship.setVelX(Ship.getvL());
	}

	/*****************************************************************
	 * This method returns the ship in the game.
	 * 
	 * @return ship - the ship in the game.
	 *****************************************************************/
	public Ship getShip() {
		return ship;
	}

	/*****************************************************************
	 * This method sets the ship in the game.
	 * 
	 * @param ship
	 *            - the ship in the game.
	 *****************************************************************/
	public void setShip(final Ship ship) {
		this.ship = ship;
	}

	/*****************************************************************
	 * This method sets the ship in the game.
	 * 
	 * @return boss - the boss in the game.
	 *****************************************************************/
	

	/*****************************************************************
	 * This method returns the arraylist of enemies in the game.
	 * 
	 * @return enemies - the Enemies in the game.
	 *****************************************************************/
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/*****************************************************************
	 * This method returns the arraylist of projectiles in the game.
	 * 
	 * @return projectiles - the projectiles in the game.
	 *****************************************************************/
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}


	
	/*****************************************************************
	 * This method returns the arraylist of space objects in the game.
	 * 
	 * @return spaceObjects - all the space things.
	 *****************************************************************/
	public ArrayList<SpaceObject> getSpaceObjects() {
		return spaceObjects;
	}

	/******************************************************************
	 * This method detects collisions between projectiles and Enemies 
	 * and adds the corresponding enemies to deadEnemies and the 
	 * projectiles to spentProjectiles.
	 *****************************************************************/
	public void detectCollisions() {
		for (Enemy e : enemies) {
			/** handled with SpaceObject.move() */
			//if (e.getY() <= Scaler.height + ship.getHeight()) {
			//	e.setY(e.getY() + e.getVelY());
			//}

			// determine if enemy collide with the ship
			if (e.getY() >= Scaler.HEIGHT) {
				enemyMissed++;
				deadEnemies.add(e);
				e.doLogic();
				e.setHealth(e.getHealth() - 1);
				
			}

			for (Projectile p : projectiles) {

				// determine if the shot hits the enemy
				if (e.collide(p)) {
					e.setShot();
					spentProjectiles.add(p);
					e.setHealth(e.getHealth() - p.getDMG());
				// check health, add dead enemy to the array
					if (e.getHealth() <= 0) {
						spentProjectiles.add(p);
				// add one to every enemy destroy
						numEnemyDestroyed++;
						deadEnemies.add(e);
						e.doLogic();
						e.setHealth(e.getHealth() - 1);

					}
				}
			}
		}
	}

	/******************************************************************
	 * This method adds a new enemy to enemies ArrayList.
	 *****************************************************************/
	public void spawnEnemy() {
		Enemy e = new Enemy(1);
		enemies.add(e);
		addSpaceCadet(e);
	}


	/******************************************************************
	 * This method retrieves the spentProjectiles ArrayList.
	 * 
	 * @return spentProjectiles - an ArrayList of dead projectiles
	 *****************************************************************/
	public ArrayList<Projectile> getSpentProjectiles() {
		return spentProjectiles;
	}

	/******************************************************************
	 * This method retrieves the deadEnemies ArrayList.
	 * 
	 * @return deadEnemies - an arrayList of the dead Enemies
	 *****************************************************************/
	public ArrayList<Enemy> getDeadEnemies() {
		return deadEnemies;
	}

	/******************************************************************
	 * This method checks to see if the ship is within the game boundaries.
	 *****************************************************************/
	public void checkShipPosition() {

		if (ship.getX() <= 0) {
			stopShip();
			ship.setX(0);
		}
		if (ship.getX() >= ship.getMaxX()) {
			stopShip();
			ship.setX(ship.getMaxX());
		}

	}

}
