package nachogame;

/**********************************************************************
 * This class creates a player object for the game. The player can
 * accumulate xp which then allows them to rank up. When a player
 * increases rank there fire rate will increase.
 * @author jonnyd_went
 *********************************************************************/
public class Player {

	/** The experience gained by the player. */
	private int xp;
	
	/** The rank of the Player. */
	private Rank rank;
	
	/** The fire rate of the player based on rank. */
	private int fireRate;
	
	/******************************************************************
	 * This is the default constructor for Player.
	 *****************************************************************/
	public Player() {
		xp = 20;
		getRank();
	}

	/******************************************************************
	 * This method sets the fire rate of the player based on their rank.
	 * @return rank - The current Rank of the player.
	 ******************************************************************/
	protected Rank getRank() {
		if (xp > 1000) {
			rank = Rank.CEO;
			fireRate = 16;

		} else if (xp > 750) {
			rank = Rank.DIRECTOR;
			fireRate = 20;
		
		} else if (xp > 600) {
			rank = Rank.DISTRICT_SUPERVISOR;
			fireRate = 25;
		
		} else if (xp > 500) {
			rank = Rank.FRANCHISE_OWNER;
			fireRate = 30;
		
		} else if (xp > 400) {
			rank = Rank.STORE_MANAGER;
			fireRate = 35;

		} else if (xp > 300) {
			rank = Rank.NACHO_ASSISTANT;
			fireRate = 38;

		} else if (xp > 200) {
			rank = Rank.A_CUT_ABOVE_THE_CHEESE;
			fireRate = 40;

		} else if (xp > 100) {
			rank = Rank.LIMPNACHO;
			fireRate = 45;

		} else  {
			rank = Rank.CRUMBS;
			fireRate = 50;
		}
		
		return rank;
	}
	
	/******************************************************************
	 * This method returns a string representation of rank.
	 * @param rank - A Rank passed to the function.
	 * @return - A string representation of rank.
	 ******************************************************************/
	public String rankToString(final Rank rank) {
		switch (rank) {
		case CRUMBS:
			return "CRUMBS";
		
		case LIMPNACHO:
			return "LIMPNACHO";
		
		case A_CUT_ABOVE_THE_CHEESE:
			return "A CUT ABOVE THE CHEESE";
		
		case NACHO_ASSISTANT:
			return "NACHO ASSISTANT";
			
		case STORE_MANAGER:
			return "STORE MANAGER";
			
		case FRANCHISE_OWNER:
			return "FRANCHISE OWNER";
			
		case DISTRICT_SUPERVISOR:
			return "DISTRICT SUPERVISOR";
			
		case DIRECTOR:
			return "DIRECTOR";
			
		case CEO:
			return "CEO";
			
		default:
			return "CRUMBS";
		}
	}
	
	/******************************************************************
	 * This method returns the fire rate of the player.
	 * @return fireRate - the fireRate of the player.
	 ******************************************************************/
	public int getFireRate() {
		getRank();
		return fireRate;
	}
	
	
	/******************************************************************
	 * This method returns the xp of the player.
	 * @return xp - the xp of the player.
	 ******************************************************************/
	public int getXP() {
		return xp;
	}
	
	/******************************************************************
	 * This method sets the xp of the player.
	 * @param xp - the xp of the player.
	 ******************************************************************/
	public void setXP(final int xp) {
		this.xp = xp;
	}
	
	/******************************************************************
	 * This method increments the xp of the player.
	 ******************************************************************/
	public void incrementXP() {
		xp++;
	}
	
	/******************************************************************
	 * This method decrements the xp of the player.
	 ******************************************************************/
	public void decrementXP() {
		xp -= 5;
	}
	
	
	
}
