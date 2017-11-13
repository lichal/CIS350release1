package nachogame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

/**********************************************************************
 * This is a Enemy class for the space nacho game. The class will perform the
 * graphic resize for enemies, detect collisions for the enemies, and determine
 * if the enemies are destroyed or not.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-24-2017
 *********************************************************************/
public class Enemy extends SpaceObject {

	/** This int is used for the animation. */
	private int counting;

	/** This Boolean determines if the enemy is destroyed or not. */
	private Boolean destroyed;

	/** The rectangle for enemy to determine a collision. */
	private Rectangle enemy = new Rectangle();

	/** The rectangle for shoot determine a collision. */
	private Rectangle shoot = new Rectangle();

	/*****************************************************************
	 * This is the constructor for Enemy.
	 * 
	 * @param health
	 *            current health of the enemy
	 *****************************************************************/
	public Enemy(final int health) {
		super(1.0, 0.0655, "Art/Nasty_Nacho_Lime.png");
		destroyed = false;

		Random r = new Random();
		setX(r.nextInt(Scaler.width - getWidth()));
		setY(-getHeight());
		velY = (int) Math.ceil(.00111 * Scaler.height);
		

		counting = 0;
	}

	/******************************************************************
	 * @return true if the enemy is destroyed
	 *****************************************************************/
	public boolean getDestroyed() {
		return destroyed;
	}

	/******************************************************************
	 * @param destroyed
	 *            true if the enemy is destroyed
	 *****************************************************************/
	public void setDestroyed(final boolean destroyed) {
		this.destroyed = destroyed;
	}

	/******************************************************************
	 * @param collide
	 *            true if the enemy collides with something else
	 * @param img
	 *            explosion graphic
	 *****************************************************************/
	private void detectDestroyed(final Boolean collide, final String img) {
		this.setImage(img);
		destroyed = collide;
		setImage(getImage().getScaledInstance(
				100, 70, Image.SCALE_DEFAULT));
	}

	/*******************************************************************
	 * This method is used to determine if projectiles collide with 
	 * enemies. It will creates two rectangles and determine if the
	 * two rectangles intersects each
	 * other.
	 * 
	 * @param other
	 *            this other is the projectile shoot by the player
	 * @return true if the projectile intersects with the enemy
	 ******************************************************************/
	public boolean collide(final Projectile other) {
		enemy.setBounds(getX(), getY(), getWidth(), getHeight());
		shoot.setBounds(other.getX(), other.getY(), 
				other.getWidth(), other.getHeight());

		return enemy.intersects(shoot);
	}

	/******************************************************************
	 * This method determines some logic of the game, such as 
	 * determine dead enemy. It will set enemy graphic to an 
	 * explosion graphic if the enemy health is zero.
	 *****************************************************************/
	@Override
	public void doLogic() {
		if (this.getHealth() <= 0) {
			detectDestroyed(true, "Art/explode.png");
		}
	}

	/******************************************************************
	 * This method sets the counting int.
	 * @param counting - The value counting will be set to.
	 *****************************************************************/
	public void setCounting(final int counting) {
		this.counting = counting;
	}

	/******************************************************************
	 * This method gets the counting variable.
	 * @return counting - The enemy counting variable.
	 *****************************************************************/
	public int getCounting() {
		return counting;
	}

	/******************************************************************
	 * This method gets the rectangle of the enemy. Used for detecting
	 * collisions.
	 * @return enemy - a rectangle the size of the enemy.
	 *****************************************************************/
	public Rectangle getEnemy() {
		return enemy;
	}

	/******************************************************************
	 * This method gets the rectangle for projectile.
	 * @return shoot - a rectangle for projectile.
	 *****************************************************************/
	public Rectangle getShoot() {
		return shoot;
	}

	/******************************************************************
	 * This method returns whether or not the enemy is destroyed.
	 * @param destroyed - the status of the ship.
	 *****************************************************************/
	public void setDestroyed(final Boolean destroyed) {
		this.destroyed = destroyed;
	}
}
