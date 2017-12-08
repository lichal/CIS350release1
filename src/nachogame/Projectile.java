package nachogame;



/**********************************************************************
 * This is a Projectile class for spaceNachos.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 *********************************************************************/
public class Projectile extends SpaceObject {
	/** The damage the projectile inflicts. */
	private int dmg;

	/*****************************************************************
	 * The constructor for Projectile.
	 * @param s - The ship fires projectile
	 *****************************************************************/
	public Projectile(final Ship s) {
		super(3.0, 0.0085, "Art/Disgusting_Mustard_Yellow.png");
		setX((int) (s.getMiddle() - 0.5 * getWidth()));
		setY(Scaler.HEIGHT - s.getHeight() - getHeight());
		velY = -Scaler.HEIGHT / 100;
		dmg = 1;
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

	@Override
	public void doLogic() {
		// TODO Auto-generated method stub
		
	}

}
