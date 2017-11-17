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
	/** The arrayList of tables in the game. */
	private ArrayList<Table> tables;
	/** The ArrayList of pissed off tables in the game. */
	private ArrayList<Table> pissedOffTables;
	/** The background for the game. */
	private BackGround background;
	/** The boss in the game. */
	private TacoBoss boss;

	/******************************************************************
	 * The constructor for Logic.
	 *****************************************************************/
	public Logic() {

		tables = new ArrayList<Table>();

		setInitialTables();

		background = new BackGround();

		ship = new Ship();
		enemies = new ArrayList<Enemy>();

		// boss = new TacoBoss("Art/tacosalad.png", 225, -200, 25);
		projectiles = new ArrayList<Projectile>();

		spentProjectiles = new ArrayList<Projectile>();

		deadEnemies = new ArrayList<Enemy>();
	}

	/******************************************************************
	 * This method gets the background for the game.
	 * 
	 * @return background - the game background.
	 *****************************************************************/
	public BackGround getBackGround() {
		return background;
	}

	/*****************************************************************
	 * This method moves the projectiles vertically as well as return the
	 * projectiles that are to be removed from the projectiles ArrayList.
	 ****************************************************************/
	public void moveProjectiles() {

		/*
		 * looping through decrementing y position and adding expired projectiles to
		 * spentProjectiles
		 */
		for (Projectile x : projectiles) {
			if (x.getY() < -10) {
				spentProjectiles.add(x);
			}
			x.setY(x.getY() + x.getVelY());
		}
	}

	/*****************************************************************
	 * This method sets ships x velocity to 0.
	 *****************************************************************/
	public void stopShip() {
		ship.setVelX(Ship.V0);
	}

	/*****************************************************************
	 * This method increments x velocity to drive right.
	 *****************************************************************/
	public void driveRight() {
		ship.setVelX(Ship.VR);
	}

	/*****************************************************************
	 * This method decrements x velocity to drive left.
	 *****************************************************************/
	public void driveLeft() {
		ship.setVelX(Ship.VL);
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
	public TacoBoss getBoss() {
		return boss;
	}

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
	 * This method returns the arraylist of tables in the game.
	 * 
	 * @return tables - the tables in the game.
	 *****************************************************************/
	public ArrayList<Table> getTables() {
		return tables;
	}

	/*****************************************************************
	 * This method returns the arraylist of tables in the game.
	 *****************************************************************/
	public void setInitialTables() {
		tables.add(new Table("Art/table.png", 50, 1));
		tables.add(new Table("Art/table.png", 250, 1));
		tables.add(new Table("Art/table.png", 450, 1));
		tables.add(new Table("Art/table.png", 650, 1));
		System.out.println(tables.size());
	}

	/*****************************************************************
	 * This method returns the arraylist of pissedOffTables in the game.
	 * 
	 * @return pissedOffTables - the tables that get hit by chips.
	 *****************************************************************/
	public ArrayList<Table> getPissedOffTables() {
		return pissedOffTables;
	}

	/******************************************************************
	 * This method detects collisions between projectiles and Enemies and adds the
	 * corresponding enemies to deadEnemies and the projectiles to spentProjectiles.
	 *****************************************************************/
	public void detectCollisions() {
		for (Enemy e : enemies) {
			
			// determine if enemy collide with the ship
			if (e.getY() >= Scaler.height - ship.getHeight() - e.getHeight()) {
				e.setHealth(e.getHealth()-e.getHealth());
				deadEnemies.add(e);
				e.doLogic();
			}

			for (Projectile p : projectiles) {

				// determine if the shot hits the enemy
				if (e.collide(p)) {
					// enemy health minus projectile damage
					if (e.getHealth() > 0) {
						// add spent projectile to the array
						spentProjectiles.add(p);
						e.setHealth(e.getHealth() - p.getDMG());
					}

					// check health, add dead enemy to the array
					if (e.getHealth() == 0) {
						if(!e.getDestroyed()) {
							spentProjectiles.add(p);
						}
						deadEnemies.add(e);
						e.doLogic();
					}
				}
			}
		}
	}

	/******************************************************************
	 * This method adds a new enemy to enemies ArrayList.
	 * 
	 * @param x
	 *            - The x coordinate of the enemy.
	 *****************************************************************/
	public void spawnEnemy(final int x) {
		enemies.add(new Enemy(1));
	}

	/******************************************************************
	 * This method adds a new TacoBoss to enemies ArrayList.
	 *****************************************************************/
	public void spawnBoss() {
		enemies.add(new TacoBoss(10));
	}

	/******************************************************************
	 * This method moves the Enemies on the screen and deletes enemies that have
	 * traveled out of bounds.
	 *****************************************************************/
	public void moveEnemies() {
		for (Enemy e : enemies) {
			if (e.getDestroyed()) {
				e.setY(e.getY());
				e.doLogic();
				deadEnemies.add(e);
			}
			else if (e.getY() <= Scaler.height - ship.getHeight() - e.getHeight()) {
				e.setY(e.getY() + e.getVelY());
			}
		}
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

	/******************************************************************
	 * This method moves the ship (sauce shooter).
	 *****************************************************************/
	public void moveShip() {
		getShip().setX(getShip().getX() + getShip().getVelx());
	}

}
