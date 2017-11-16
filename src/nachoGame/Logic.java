package nachoGame;

import java.util.ArrayList;

import javax.swing.JPanel;

/**********************************************************************
 * This is the game engine for our space nachos game
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 *********************************************************************/
public class Logic extends JPanel {
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Projectile> spentProjectiles;
	private ArrayList<Enemy> deadEnemies;
	private ArrayList<Table> tables;
	private ArrayList<Table> pissedOffTables;
	private BackGround background;
	private TacoBoss boss;

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

	public BackGround getBackGround() {
		return background;
	}

	/*****************************************************************
	 * This method moves the projectiles vertically as well as return the
	 * projectiles that are to be removed from the projectiles ArrayList.
	 * 
	 * @return p - an arrayList of projectiles to be removed from the game
	 ***********************************************************************/
	public void moveProjectiles() {

		/*
		 * looping through decrementing y position and adding expired
		 * projectiles to spentProjectiles
		 */
		for (Projectile x : projectiles) {
			if (x.getY() < -10)
				spentProjectiles.add(x);
			x.setY(x.getY() - x.getpVel());
		}
	}

	/*****************************************************************
	 * This method sets ships x velocity to 0
	 *****************************************************************/
	public void stopShip() {
		ship.setVelX(Ship.V0);
	}

	/*****************************************************************
	 * This method increments x velocity to drive right
	 *****************************************************************/
	public void driveRight() {
		ship.setVelX(Ship.VR);
	}

	/*****************************************************************
	 * This method decrements x velocity to drive left
	 *****************************************************************/
	public void driveLeft() {
		ship.setVelX(Ship.VL);
	}

	/*****************************************************************
	 * This method returns the ship in the game
	 *****************************************************************/
	public Ship getShip() {
		return ship;
	}

	/*****************************************************************
	 * This method sets the ship in the game
	 *****************************************************************/
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public TacoBoss getBoss() {
		return boss;
	}

	/*****************************************************************
	 * This method returns the arraylist of enemies in the game
	 *****************************************************************/
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/*****************************************************************
	 * This method returns the arraylist of projectiles in the game
	 *****************************************************************/
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	/*****************************************************************
	 * This method returns the arraylist of tables in the game
	 *****************************************************************/
	public ArrayList<Table> getTables() {
		return tables;
	}

	/*****************************************************************
	 * This method returns the arraylist of tables in the game
	 *****************************************************************/
	public void setInitialTables() {
		tables.add(new Table("Art/table.png", 50, 1));
		tables.add(new Table("Art/table.png", 250, 1));
		tables.add(new Table("Art/table.png", 450, 1));
		tables.add(new Table("Art/table.png", 650, 1));
		System.out.println(tables.size());
	}
	/*****************************************************************
	 * This method returns the arraylist of pissedOffTables in the game
	 *****************************************************************/
	public ArrayList<Table> getPissedOffTables() {
		return pissedOffTables;
	}

	/******************************************************************
	 * This method detects collisions between projectiles and Enemies and adds
	 * the corresponding enemies to deadEnemies and the projectiles to
	 * spentProjectiles
	 *****************************************************************/
	public void detectCollisions() {
		for (Enemy e : enemies) {
			if(e.getY() >= 600) {
				e.setHealth(0);
				deadEnemies.add(e);
				ship.health--;
			}
			for (Projectile p : projectiles) {
				
				// determine if the shot hits the enemy
				if (e.collide(p)) {
					
					// enemy health minus projectile damage
					if(e.getHealth() > 0) {
						// add spent projectile to the array
						spentProjectiles.add(p);
						e.setHealth(e.getHealth() - p.getDMG());
					}
					
					// check health, add dead enemy to the array
					if (e.getHealth() == 0) {
						spentProjectiles.add(p);
						deadEnemies.add(e);
						e.doLogic();
					}				
				}
			}
		}
	}

	/******************************************************************
	 * This method adds a new enemy to enemies ArrayList
	 *****************************************************************/
	public void spawnEnemy(int x) {
		enemies.add(new Enemy(1));
	}

	public void spawnBoss() {
		enemies.add(new TacoBoss(10));
	}

	/******************************************************************
	 * This method moves the Enemies on the screen and deletes enemies that have
	 * traveled out of bounds
	 *****************************************************************/
	public void moveEnemies() {
		for (Enemy e : enemies) {
			if(e.getDestroyed()) {
				e.setY(e.getY());
				e.setX(e.getX());
			}
			else if(e.getY() < 580) {
				e.setY(e.getY() + e.getVelY());
			}
		}
	}

	/******************************************************************
	 * This method retrieves the spentProjectiles ArrayList
	 * 
	 * @return spentProjectiles - an ArrayList of dead projectiles
	 *****************************************************************/
	public ArrayList<Projectile> getSpentProjectiles() {
		return spentProjectiles;
	}

	/******************************************************************
	 * This method retrieves the deadEnemies ArrayList
	 * 
	 * @return deadEnemies - an arrayList of the dead Enemies
	 *****************************************************************/
	public ArrayList<Enemy> getDeadEnemies() {
		return deadEnemies;
	}

	/******************************************************************
	 * This method checks to see if the ship is within the game boundaries
	 *****************************************************************/
	public void checkShipPosition() {
		
		if(ship.getX() <= 0) {
			stopShip();
			ship.setX(0);
		}
		if(ship.getX() >= ship.getMaxX()) {
			stopShip();
			ship.setX(ship.getMaxX());
		} 

	}

	/******************************************************************
	 * This method moves the ship (sauce shooter)
	 *****************************************************************/
	public void moveShip() {
		getShip().setX(getShip().getX() + getShip().getVelx());
	}

}
