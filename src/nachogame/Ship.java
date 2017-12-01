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
	protected static int V0; 
	/** The velocity when the ship is driving left. */
	protected static int VL; 
	/** The velocity when the ship is driving right. */
	protected static int VR;

	/*****************************************************************
	 * The constructor for ship.
	 ****************************************************************/
	public Ship() {
		super(1.0, 0.085, "Art/Disgusting_Mustard_Yellow.png");
		setX(Scaler.width / 2);
		setY(Scaler.height - getHeight());
		V0 = 0;
		VL = -Scaler.width/200;
		VR = Scaler.width/200;
	}

	@Override
	public void doLogic() {
		// TODO Auto-generated method stub

	}

}
