package nachogame;

/**********************************************************************
 * This is the ship class which inherits from space object
 * nacho game.
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-24-2017
 *
 *********************************************************************/
// ALSFdjlasdjf;lasjdf;lajs;dlfjas;ldfja;sldkjf
public class Ship extends SpaceObject {

	/** The velocity when ship is stopped. */
	private static final int  v0 = 0; 
	/** The velocity when the ship is driving left. */
	private static final int vL = -Scaler.width / 200; 
	/** The velocity when the ship is driving right. */
	private static final int vR = Scaler.width / 200;

	/*****************************************************************
	 * The constructor for ship.
	 ****************************************************************/
	public Ship() {
		super(1.0, 0.085, "Art/Disgusting_Mustard_Yellow.png");
		setX(Scaler.width / 2);
		setY(Scaler.height - getHeight());
//		v0 = 0;
//		vL = -Scaler.width / 200;
//		vR = Scaler.width / 200;
	}

	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

	public static int getV0() {
		return v0;
	}


	public static int getvL() {
		return vL;
	}

	public static int getvR() {
		return vR;
	}

}
