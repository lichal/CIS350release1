package nachoGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

/**********************************************************************
 * This is a Enemy class for the space nacho game. The class will 
 * perform the graphic resize for enemies, detect collisions for the 
 * enemies, and determine if the enemies are destroyed or not.
 * 
 * @author Jon DeWent 
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-24-2017
 *********************************************************************/
public class Enemy extends SpaceObject {
	
	private int counting;
	
	/** This Boolean determines if the enemy is destroyed or not. */
	protected Boolean destroyed;
	
	/** The rectangle for enemy to determine a collision */
	private Rectangle enemy = new Rectangle();
	
	/** The rectangle for shoot determine a collision */
	private Rectangle shoot = new Rectangle();

	/*****************************************************************
	 * This is the constructor for Enemy
	 * 
	 * @param health
	 *            current health of the enemy
	 *****************************************************************/
	public Enemy(int health) {
		super(1.0, 0.0655, "Art/Nasty_Nacho_Lime.png");
		destroyed = false;
		
		Random r = new Random();
		x = r.nextInt(Scaler.width - width);
		y = -height;
		velY = (int)Math.ceil(.00111 * Scaler.height);
		
		counting = 0;
	}

	/******************************************************************
	 * @return true if the enemy is destroyed
	 *****************************************************************/
	public boolean getDestroyed() {
		return destroyed;
	}

	/******************************************************************
	 * @param destroyed true if the enemy is destroyed
	 *****************************************************************/
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	/******************************************************************
	 * @param collide true if the enemy collides with something
	 * @param img explosion graphic
	 *****************************************************************/
	private void detectDestroyed(Boolean collide, String img) {
		this.setImage(img);
		destroyed = collide;

		image = image.getScaledInstance(100, 70, Image.SCALE_DEFAULT);	
	}
	
	/*******************************************************************
	 * This method is used to determine if projectiles collide with 
	 * enemies. It will creates two rectangles and determine if the two 
	 * rectangles intersects each other. 
	 * 
	 * @param other this other is the projectile shoot by the player
	 * @return true if the projectile intersects with the enemy
	 ******************************************************************/
	public boolean collide(Projectile other) {
		enemy.setBounds(this.x, this.y, this.width,this.height);
		shoot.setBounds(other.getX(), other.getY(), other.getWidth(),other.getHeight());

		return enemy.intersects(shoot);
	} 

	/******************************************************************
	 * This method determines some logic of the game, such as determine
	 * dead enemy. It will set enemy graphic to an explosion graphic
	 * if the enemy health is zero.
	 *****************************************************************/
	@Override
	public void doLogic() {
		if (this.getHealth() <= 0) {
			detectDestroyed(true, "Art/explode.png");
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setCounting(int counting){
		this.counting = counting;
	}
	
	public int getCounting() {
		return counting;
	}
}
