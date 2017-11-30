package nachogame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**********************************************************************
 * This class creates object for the game which enemy, ship and enemy
 * inherit from.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 * @version 10-13-2017
 *********************************************************************/
public abstract class SpaceObject {
	
	/** x position of the current instance of entity. */
	private int x;

	/** y position of the current instance of entity. */
	private int y;

	/** Health of the current instance of entity. */
	private int health;

	/** Current entity graphic. */
	private Image image;

	/** Controls the size of the object. */
	private double aspectRatio;

	/** controls the portion of the screen the object uses. */
	private double portion;

	/** The width of the SpaceObject. */
	private int width;

	/** The height of the object. */
	private int height;

	/** The velocity of the spaceObject. */
	private int velX;

	/** The velocity of the spaceObject. */
	protected int velY;

	/*******************************************************************
	 * This is the constructor for SpaceObject.
	 * @param aR - the aspect ratio of the SpaceObject.
	 * @param por - the portion size of the SpaceObject.
	 * @param imageSource - the name of the file the image is from.
	 ******************************************************************/
	public SpaceObject(final double aR, final double por, 
			final String imageSource) {
		refactor(aR, por, imageSource);
	}

	/*******************************************************************
	 * Set the graphic of entity.
	 * 
	 * @param imageSource - string reference of entity graphic
	 * @param por - the portion size of the SpaceObject.
	 * @param aR - the name of the file the image is from.
	 ******************************************************************/
	public void refactor(final double aR, final double por, 
		final String imageSource) {
		aspectRatio = aR;
		portion = por;
		try {
			int[] size = Scaler.calcDimensions(
				aspectRatio, portion);
			image = ImageIO.read(new File(imageSource));
			width = size[0];
			height = size[1];
			image = image.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException m) {
			System.out.println("Size render problem!!!");
		}

		velX = 0;
		velY = 0;
	}
	
	/*******************************************************************
	 * @return aspectRatio to the screen of the object
	 ******************************************************************/
	public double getAspectRatio() {
		return aspectRatio;
	}

	/*******************************************************************
	 * @return portion of the object
	 ******************************************************************/
	public double getPortion() {
		return portion;
	}

	/*******************************************************************
	 * @return velx the speed horizontal of the object
	 ******************************************************************/
	public int getVelX() {
		return velX;
	}

	/*******************************************************************
	 * Setter for space object image
	 * @param image - image of the object
	 ******************************************************************/
	public void setImage(Image image) {
		this.image = image;
	}

	/*******************************************************************
	 * @return current x position of entity
	 ******************************************************************/
	public int getX() {
		return x;
	}

	/*******************************************************************
	 * Set the x position of the entity.
	 * @param x - x position of the entity
	 ******************************************************************/
	public void setX(final int x) {
		this.x = x;
	}

	/*******************************************************************
	 * @return current y position of entity
	 ******************************************************************/
	public int getY() {
		return y;
	}

	/*******************************************************************
	 * Set the y position of the entity.
	 * @param y - y position of the entity
	 ******************************************************************/
	public void setY(final int y) {
		this.y = y;
	}

	/*******************************************************************
	 * @return entity health
	 ******************************************************************/
	public int getHealth() {
		return health;
	}

	/*******************************************************************
	 * Set the entity health.
	 * 
	 * @param health - entity health
	 ******************************************************************/
	public void setHealth(final int health) {
		this.health = health;
	}

	/*******************************************************************
	 * Set the graphic of entity.
	 * 
	 * @param img - string reference of entity graphic
	 ******************************************************************/
	public void setImage(final String img) {
		refactor(aspectRatio, portion, img);
	}

	/*******************************************************************
	 * @return entity image
	 ******************************************************************/
	public Image getImage() {
		return image;
	}

	// returns middle of image on X side
	/****************************************************************** 
	 * The line that vertically bisects the object's image.
	 * @return - the middle x of the image.
	 *  **************************************************************/
	public int getMiddle() {
		return (int) (x + (0.5 * width));
	}

	/******************************************************************
	 * @return the farthest x pos it can be drawn at
	 *****************************************************************/
	public int getMaxX() {
		return (Scaler.width - width);
	}

	/******************************************************************
	 * @return the farthest y pos it can be drawn at
	 *****************************************************************/
	public int getMaxY() {
		return (Scaler.height - height);
	}

	/******************************************************************
	 * @return an int for enemy height
	 *****************************************************************/
	public int getHeight() {
		return height;
	}

	/******************************************************************
	 * @return an int for enemy width
	 *****************************************************************/
	public int getWidth() {
		return width;
	}

	/*****************************************************************
	 * @return velX - the x velocity of the SpaceObject.
	 *****************************************************************/
	public int getVelx() {
		return velX;
	}

	/******************************************************************
	 * @return current y velocity of enemy.
	 *****************************************************************/
	public int getVelY() {
		return velY;
	}

	/*****************************************************************
	 * @param velX - the x velocity of the SpaceObject.
	 *****************************************************************/
	public void setVelX(final int velX) {
		this.velX = velX;
	}

	/*****************************************************************
	 * @param velX - the x velocity of the SpaceObject.
	 *****************************************************************/
	public void setVelY(final int velX) {
		this.velX = velX;
	}

	/*******************************************************************
	 * This abstract class contain partial logic of the entity.
	 ******************************************************************/
	public abstract void doLogic();

	/******************************************************************
	 * This method moves the SpaceObject position.
	 *****************************************************************/
	public void move() {
		x += velX;
		y += velY;
	}

}
