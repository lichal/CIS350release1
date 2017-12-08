package nachogame;
/**
 * This class is used to create and manage the levels of the game.
 * @author jonnyd_went
 *
 */
public class Level {
	/** The length of time of the level. */
	private int levelTime;
	/** Tells whether
	private boolean completed;
	/** The amount of time elapsed in level. */
	private int timeElapsed;
	
	/** One minute time length for timer with 10 millisecond delay. */
	private static int oneMinute = 6000;
	
	/** 1.5 minute time length for timer with 10 millisecond delay. */
	private static int minuteAndHalf = 9000;
	
	/** Two minute time length for timer with 10 millisecond delay. */
	private static int twoMinutes = 12000;
	
	/** A boolean that states when an enemy should be spawned. */
	private boolean spawnEnemy;
	
	/** A boolean that states when a boss should be spawned. */
	private boolean spawnBoss;
	
	/** Padding time that ensures enemies are not still on screen when 
	time is up. */
	private static int paddingTime = 500;
	
	/** The levelNum for the corresponding level the panel is displaying. */
	private LevelNum level;
	
	/******************************************************************
	 * This is the constructor for Level. The Level is created with 
	 * different difficulties and time lengths depending of parameter level
	 * @param level - An Enum that represents level number
	 ******************************************************************/
	public Level(final LevelNum level) {
		this.level = level;
	}

	/******************************************************************
	 * This method runs the level the game is on.
	 ******************************************************************/
	public void run() {
		switch (level) {
		case LEVEL1 :
			levelTime = oneMinute;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 300 == 0) {
					spawnEnemy = true;
				}
				}
			break;
		case LEVEL2 :
			levelTime = minuteAndHalf;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 270 == 0) {
					spawnEnemy = true;
				}
				}
			break;
		case LEVEL3 :
			levelTime = minuteAndHalf;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 240 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 7000 == 0) {
					spawnBoss = true;
				}
				}
			break;
			
		case LEVEL4 :
			levelTime = minuteAndHalf;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 190 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 3000 == 0) {
					spawnBoss = true;
				}
				}
			break;
			
		case LEVEL5 :
			levelTime = twoMinutes;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 170 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 7000 == 0) {
					spawnBoss = true;
				}
				}
			break;
			
		case LEVEL6 :
			levelTime = twoMinutes;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 125 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 4000 == 0) {
					spawnBoss = true;
				}
				}
			break;
			
		case LEVEL7 :
			levelTime = twoMinutes;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 100 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 4000 == 0) {
					spawnBoss = true;
				}
				}
			break;
			
		case LEVEL8 :
			levelTime = twoMinutes;
			if (timeElapsed < levelTime - paddingTime) {
				if (timeElapsed % 75 == 0) {
					spawnEnemy = true;
				}
				if (timeElapsed % 3000 == 0) {
					spawnBoss = true;
				}
				}
			break;
		default:
			break;
		
		}
	}
	/**
	 * This method increments timeElapsed.
	 */
	public void incrementTime() {
		timeElapsed++;
	}
	
	/**
	 * This method returns timeElapsed.
	 * @return timeElapsed
	 */
	public int getTime() {
		return timeElapsed;
	}

	/******************************************************************
	 * This method sets the spawn booleans to false to prevent continuous
	 * spawning.
	 ******************************************************************/
	public void manageSpawn() {
		spawnEnemy = false;
		spawnBoss = false;
	}
	
	/******************************************************************
	 * This method gets the spawnEnemy boolean.
	 * @return spawnEnemy
	 ******************************************************************/
	public boolean getSpawnEnemy() {
		return spawnEnemy;
	}
	
	/******************************************************************
	 * This method gets the spawnBoss boolean.
	 * @return spawnBoss
	 ******************************************************************/
	public boolean getSpawnBoss() {
		return spawnBoss;
	}
	
	/******************************************************************
	 * This method gets the levelTime.
	 * @return levelTime
	 ******************************************************************/
	public int getLevelTime() {
		return levelTime;
	}
}
