package nachogame;

/**********************************************************************
 * This is a Projectile class for spaceNachos.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 *********************************************************************/
public class Projectile {
	/** The x position of the projectile. */
	private int x;
	/** The y position of the projectile. */
	private int y;
	/** The height of the projectile. */
	private int height;
	/** The width of the projectile. */
	private int width;
	/** The damage the projectile inflicts. */
	private int dmg = 1;
	/** The velocity of the projectile. */
	private final int pVel = 5;

	/*****************************************************************
	 * The constructor for Projectile.
	 * @param x - the x position of the projectile.
	 * @param y - the y position of the projectile.
	 * @param height - the height of the projectile.
	 * @param width - the width of the projectile.
	 *****************************************************************/
	public Projectile(final int x, final int y, final int height,
			final int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	/*****************************************************************
	 * This method returns the projectile velocity.
	 * @return the velocity of the projectile
	 *****************************************************************/
	public int getpVel() {
		return pVel;
	}

	/*****************************************************************
	 * This method returns the damage of the projectile.
	 * @return dmg - the dmg of the projectile.
	 *****************************************************************/
	public int getDMG() {
		return dmg;
	}

	/*****************************************************************
	 * This method sets the damage of projectiles.
	 * @param dMG - the dameage.
	 *****************************************************************/
	public void setDMG(final int dMG) {
		dmg = dMG;
	}

	/*****************************************************************
	 * This method gets the x coordinates of a projectile.
	 * @return x - the x coordinate.
	 *****************************************************************/
	public int getX() {
		return x;
	}

	/*****************************************************************
	 * This method sets the x coordinates of a projectile.
	 * @param x - the x coordinate.
	 *****************************************************************/
	public void setX(final int x) {
		this.x = x;
	}

	/*****************************************************************
	 * This method gets the y coordinates of a projectile.
	 * @return y - the y coordinate
	 *****************************************************************/
	public int getY() {
		return y;
	}

	/*****************************************************************
	 * This method sets the y coordinates of the projectile.
	 * @param y - the y coordinate the projectile be set to.
	 *****************************************************************/
	public void setY(final int y) {
		this.y = y;
	}

	/*****************************************************************
	 * This method gets the height of the projectile. 
	 * @return height
	 *****************************************************************/
	public int getHeight() {
		return height;
	}

	/*****************************************************************
	 * This method sets the height of the projectile.
	 * @param height - the height of the projectile.
	 *****************************************************************/
	public void setHeight(final int height) {
		this.height = height;
	}

	/*****************************************************************
	 * This method gets the width of the projectile.
	 * @return width 
	 *****************************************************************/
	public int getWidth() {
		return width;
	}

	/*****************************************************************
	 * This method sets the width of the projectile.
	 * @param width 
	 *****************************************************************/
	public void setWidth(final int width) {
		this.width = width;
	}

}
