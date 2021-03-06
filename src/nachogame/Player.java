package nachogame;

public class Player {

	private int xp;
	private Rank rank;
	private int fireRate;
	
	public Player() {
		xp = 20;
		getRank();
	}

	protected Rank getRank() {
		if(xp > 1000) {
			rank = Rank.CEO;
			fireRate = 16;
		}
		
		else if(xp > 750) {
			rank = Rank.DIRECTOR;
			fireRate = 20;
		}
		
		else if(xp > 600) {
			rank = Rank.DISTRICT_SUPERVISOR;
			fireRate = 25;
		}
		
		else if(xp > 500) {
			rank = Rank.FRANCHISE_OWNER;
			fireRate = 30;
		}
		
		else if(xp > 400) {
			rank = Rank.STORE_MANAGER;
			fireRate = 35;
		}
		
		else if (xp > 300) {
			rank = Rank.NACHO_ASSISTANT;
			fireRate = 38;
		}
		
		else if (xp > 200) {
			rank = Rank.A_CUT_ABOVE_THE_CHEESE;
			fireRate = 40;
		}
		else if (xp > 100) {
			rank = Rank.LIMPNACHO;
			fireRate = 45;
		}
		
		else  {
			rank = Rank.CRUMBS;
			fireRate = 50;
		}
		
		return rank;
	}
	
	
	public String rankToString(Rank rank) {
		switch(rank) {
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
	public int getFireRate() {
		getRank();
		return fireRate;
	}
	
	public int getXP() {
		return xp;
	}
	
	public void setXP(int xp) {
		this.xp = xp;
	}
	
	public void incrementXP() {
		xp++;
	}
	
	public void decrementXP() {
		xp -= 5;
	}
	
	
	
}
