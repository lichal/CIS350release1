package nachogame;

/**********************************************************************
 * This is a TacoBoss class which inherits from enemy for the space
 *  nacho game.
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-24-2017
 *********************************************************************/
public class TacoBoss extends Enemy {

	/*****************************************************************
	 * This is the constructor for tacoBoss.
	 * @param health
	 ****************************************************************/
	public TacoBoss(int health) {
		super(health);
		refactor(0.75, 0.10, "Art/tacosalad.png");
		setDestroyed(false);
	}
}
