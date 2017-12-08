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
	private static final int  V0 = 0; 
	/** The velocity when the ship is driving left. */
	private static final int VL = -Scaler.WIDTH / 200; 
	/** The velocity when the ship is driving right. */
	private static final int VR = Scaler.WIDTH / 200;

	/*****************************************************************
	 * The constructor for ship.
	 ****************************************************************/
	public Ship() {
		super(1.0, 0.085, "Art/Disgusting_Mustard_Yellow.png");
		setX(Scaler.WIDTH / 2);
		setY(Scaler.HEIGHT - getHeight());
	}

	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

	/*****************************************************************
	 * This method gets the velocity of the ship when its stopped.
	 * @return V0 - The velocity when ship is stopped.
	 *****************************************************************/
	public static int getV0() {
		return V0;
	}


	/******************************************************************
	 * This method gets the velocity of the ship when driving left.
	 * @return VL - the left velocity of the ship.
	 *****************************************************************/
	public static int getvL() {
		return VL;
	}

	/******************************************************************
	 * This method gets the velocity of the ship when driving right.
	 * @return VR - the right velocity of the ship.
	 *****************************************************************/
	public static int getvR() {
		return VR;
	}

}
