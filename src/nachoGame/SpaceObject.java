package nachoGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SpaceObject {
	/** x position of the current instance of entity. */
	protected int x;

	/** y position of the current instance of entity. */
	protected int y;

	/** Health of the current instance of entity. */
	protected int health;

	/** Current entity graphic */
	protected Image image;
	
	protected double aspectRatio;
	
	protected double portion;
	
	protected int width;
	
	protected int height;
	
	protected int velX;
	
	protected int velY;
	
	/*******************************************************************
	 * @param aR
	 * @param por
	 ******************************************************************/
	public SpaceObject(double aR, double por, String imageSource) {
		refactor(aR, por, imageSource);
	}
	
	/*******************************************************************
	 * Set the graphic of entity
	 * 
	 * @param img
	 *            string reference of entity graphic
	 ******************************************************************/
	public void refactor(double aR, double por, String imageSource) {
		aspectRatio = aR;
		portion = por;
		try {
			int size[] = Scaler.calcDimensions(aspectRatio, portion);
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
	 * @return current x position of entity
	 ******************************************************************/
	public int getX() {
		return x;
	}

	/*******************************************************************
	 * Set the x position of the entity
	 * 
	 * @param x
	 *            x position of the entity
	 ******************************************************************/
	public void setX(int x) {
		this.x = x;
	}

	/*******************************************************************
	 * @return current y position of entity
	 ******************************************************************/
	public int getY() {
		return y;
	}

	/*******************************************************************
	 * Set the y position of the entity
	 * 
	 * @param y
	 *            y position of the entity
	 ******************************************************************/
	public void setY(int y) {
		this.y = y;
	}

	/*******************************************************************
	 * @return entity health
	 ******************************************************************/
	public int getHealth() {
		return health;
	}
	
	/*******************************************************************
	 * Set the entity health
	 * 
	 * @param health
	 *            entity health
	 ******************************************************************/
	public void setHealth(int health) {
		this.health = health;
	}
	
	/*******************************************************************
	 * Set the graphic of entity
	 * 
	 * @param img
	 *            string reference of entity graphic
	 ******************************************************************/
	public void setImage(String img) {
		refactor(aspectRatio, portion, img);
	}
	
	/*******************************************************************
	 * @return entity image
	 ******************************************************************/
	public Image getImage() {
		return image;
	}
	
	/*******************************************************************
	 * @return the bisects point of the space object in the middle
	 ******************************************************************/
	/** The line that vertically bisects the object's image */
	public int getMiddle() {
		return (int) (x + (0.5 * width));
	}
	
	
	// the farthest x pos it can be drawn at
	public int getMaxX() {
		return (Scaler.width - width);
	}
	
	
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
	 * 
	 *****************************************************************/
	public int getVelx() {
		return velX;
	}
	
	/******************************************************************
	 * @return current y velocity of enemy
	 *****************************************************************/
	public int getVelY() {
		return velY;
	}
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	/*****************************************************************
	 * 
	 *****************************************************************/
	public void setVelY(int velX) {
		this.velX = velX;
	}
	
	/*******************************************************************
	 * This abstract class contain partial logic of the entity
	 ******************************************************************/
	
	// move thing
	public void move() {
		x += velX;
		y += velY;
	}
	
}
