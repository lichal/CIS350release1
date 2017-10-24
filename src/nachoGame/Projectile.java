package nachoGame;

/**********************************************************************
 * This is a Projectile class for spaceNachos
 * @author Jon DeWent
 *
 *********************************************************************/
public class Projectile {
	private int x;
	private int y;
	private int height;
	private int width;
	private int DMG = 1;
	private final int pVel = 5;
	
	
	/*****************************************************************
	 * The constructor for Projectile
	 *****************************************************************/
	public Projectile(int x,int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	/*****************************************************************
	 * This method returns the projectile velocity 
	 *****************************************************************/
	public int getpVel() {
		return pVel;
	}
	
	/*****************************************************************
	 * This method returns the damage of the projectile
	 *****************************************************************/
	public int getDMG() {
		return DMG;
	}
	
	/*****************************************************************
	 * This method sets the damage of projectiles
	 *****************************************************************/
	public void setDMG(int dMG) {
		DMG = dMG;
	}

	/*****************************************************************
	 * This method gets the x coordinates of a projectile
	 *****************************************************************/
	public int getX() {
		return x;
	}
	
	/*****************************************************************
	 * This method sets the x coordinates of a projectile
	 *****************************************************************/
	public void setX(int x) {
		this.x = x;
	}
	
	/*****************************************************************
	 * This method gets the y coordinates of a projectiles
	 *****************************************************************/
	public int getY() {
		return y;
	}
	
	/*****************************************************************
	 * This
	 *****************************************************************/
	public void setY(int y) {
		this.y = y;
	}

	/*****************************************************************
	 * This
//	 *****************************************************************/
	public int getHeight() {
		return height;
	}

	/*****************************************************************
	 * This
	 *****************************************************************/
	public void setHeight(int height) {
		this.height = height;
	}

	/*****************************************************************
	 * This
	 *****************************************************************/
	public int getWidth() {
		return width;
	}

	/*****************************************************************
	 * This
	 *****************************************************************/
	public void setWidth(int width) {
		this.width = width;
	}
	
}
